package ru.yourhockey.service.initialProductScrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.product_attributes.Brand;
import ru.yourhockey.model.product_attributes.Type;
import ru.yourhockey.repo.BrandRepo;
import ru.yourhockey.repo.ProductRepo;
import ru.yourhockey.repo.TypeRepo;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Component
@Deprecated
@RequiredArgsConstructor
public class ProductScrapper implements InitializingBean {

    /*
    ToDo Для создания модели, нельзя вырезать все русские символы из имени
    ToDo Заполнение characteristics
    ToDo
    ToDo
    * */

    public static final String BASE_PATH = "https://worldhockey.market";
    public static final String PAGINATOR_PREFIX = "?PAGEN_1=";

    Map<String, String> categories;

    private final TypeRepo typeRepository;
    private final BrandRepo brandRepository;
    private final ProductRepo productRepository;

    @Override
    public void afterPropertiesSet() {
        categories = fromJson("product-scrapper/categories.json");
    }

    public List<Product> actualizeFullProductCatalog() {
        long start = System.currentTimeMillis();
        List<Product> products = categories.entrySet().stream()
                .peek(entry -> log.info("********************** Категория {} Начала парсинг **********************", entry.getValue()))
                .map(entry -> category(entry.getKey(), convertToType(entry.getValue())))
                .flatMap(List::stream)
                .peek(productRepository::saveOrUpdate)
                .collect(Collectors.toList());
        long finish = System.currentTimeMillis();
        log.info("FULL PRODUCTS SCRAPPING TIME IS : " + (finish - start) + ". SIZE : " + products.size());
        return products;
    }

    public List<Product> category(String url, Type type) {
        long start = System.currentTimeMillis();
        List<Product> products = IntStream.range(1, definePagesCount(url) + 1)
                .mapToObj(i -> url + PAGINATOR_PREFIX + i)
                .map(categoryUrl -> oneCategoryPage(categoryUrl, type))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        long finish = System.currentTimeMillis();
        log.info("CATEGORY " + type.getShowName() + " SCRAPPING TIME IS : " + (finish - start) + ". SIZE : " + products.size());
        return products;
    }

    private List<Product> oneCategoryPage(String url, Type type) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

        return doc.getElementsByClass("item-title")
                .stream()
                .map(itemTitleClass -> itemTitleClass.getElementsByTag("a").get(0))
                .map(tagA -> tagA.attr("href"))
                .map(productSecondLinkPart -> BASE_PATH + productSecondLinkPart)
                .map(productUrl -> productPage(productUrl, type))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Product productPage(String url, Type type) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error("Failed to get product page with url {}", url);
            throw new RuntimeException("Failed to get product page with url " + url);
        }

        Brand brand = null;
        try {
            String brandShortName = doc.getElementsByClass("brand_picture").get(0)
                    .getElementsByTag("img").get(0)
                    .attr("title");
            brandShortName = brandShortName.replaceAll("[^\\w ]", "").trim();// ToDo так делать нельзя, заряд уходит
            brand = brandRepository.findByShortName(brandShortName.toUpperCase());
        } catch (Exception e) {
            try {
                List<Brand> brands = brandRepository.findAll();
                String name = doc.getElementsByClass("preview_text dotdot").get(0).text();
                brand = brands.stream()
                        .filter(b -> name.contains(b.getShortName()))
                        .findFirst().get();
            } catch (Exception er) {
                log.error("Cant take brand from product with url: {}", url);
            }
        }
        if (brand == null) {
            return null;
        }

        String description = "";
        try {
            description = doc.getElementsByClass("tabs_section").get(0)
                    .getElementsByClass("col-md-6")
                    .text().trim();
        } catch (Exception e) {
            log.error("Cant take description from product with url: {}", url);
        }

        String characteristics = "";
        try {
            characteristics = ""; //ToDo implement here
        } catch (Exception e) {
            log.error("Cant take characteristics from product with url: {}", url);
        }

        String srcImageLink = "";
        try {
            srcImageLink = doc.getElementsByClass("offers_img wof").get(0)
                    .getElementsByTag("img").get(0)
                    .attr("data-src").trim();
        } catch (Exception e) {
            srcImageLink = doc.getElementsByClass("slides").get(0)
                    .getElementsByClass("current").get(0)
                    .getElementsByTag("link").attr("href");
        }
        if (srcImageLink.isBlank()) log.error("Cant take srcImageLink from product with url: {}", url);

        String model = "";
        try {
            String name = doc.getElementsByClass("preview_text dotdot").get(0).text();
            model = getModelFromProductFullName(name, brand.getShortName(), type);
        } catch (Exception e) {
            log.error("Cant take model from product with url: {}. This product will not be created", url);
            return null;
        }

        Product product = new Product()
                .setModel(model)
                .setBrand(brand)
                .setType(type)
                .setDescription(description)
                .setCharacteristics(characteristics)
                .setSrcImageLink(srcImageLink);
        log.info(product.toString());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return product;
    }

    private String getModelFromProductFullName(String fullName, String brandShortName, Type type) {
        String name = fullName.toUpperCase();
        name = name.replaceAll(brandShortName.toUpperCase(), "");

        name = name.replaceAll(type.getUpper().toUpperCase(), "");
        if (type.getMedium() != null) {
            name = name.replaceAll(type.getMedium().toUpperCase(), "");
        }
        if (type.getLower() != null) {
            name = name.replaceAll(type.getLower().toUpperCase(), "");
        }
        if (type.getShowName() != null) {
            name = name.replaceAll(type.getShowName().toUpperCase(), "");
        }
        name = name.replaceAll("[^\\w. ]", "").trim(); //ToDo надо ли так радикально удалять?
        // ToDo так удалять нельзя, теряются русские названия (заряд прим)

        return name; //ToDo implement
    }

    private String getAgeFromName(String name) {
        String offerNameInLC = name.toLowerCase();

        if (offerNameInLC.contains("sr") || offerNameInLC.contains("senior")) {
            return "SR";
        }
        if (offerNameInLC.contains("jr") || offerNameInLC.contains("junior")) {
            return "JR";
        }
        if (offerNameInLC.contains("yth") || offerNameInLC.contains("youth")) {
            return "YTH";
        }
        if (offerNameInLC.contains("int") || offerNameInLC.contains("intermediate") || offerNameInLC.contains("intermed")) {
            return "INT";
        }
        return "";
    }

    private Integer definePagesCount(String categoryUrl) {
        Document doc;
        try {
            doc = Jsoup.connect(categoryUrl).get();
        } catch (IOException e) {
            log.error("Failed to define pages count in url {}", categoryUrl);
            throw new RuntimeException("Failed to define pages count in url" + categoryUrl);
        }

        Elements paginationElements = doc.getElementsByClass("module-pagination");
        if (paginationElements.isEmpty()) {
            return 1;
        }
        return paginationElements.get(0).getElementsByTag("a").stream()
                .map(Element::text)
                .filter(StringUtil::isNumeric)
                .mapToInt(Integer::parseInt)
                .max().getAsInt();
    }

    private Type convertToType(String typeShowName) {
        Optional<Type> oType = typeRepository.findByShowName(typeShowName);
        if (oType.isPresent()) {
            return oType.get();
        } else {
            log.error("ProductScrapper:convertToType() - There is no type with showName: {}", typeShowName);
            throw new RuntimeException("ProductScrapper:convertToType() - There is no type with showName: " + typeShowName);
        }
    }

    private static Map<String, String> fromJson(String path) {
        try {
            log.info("Reading categories from path={}", path);
            return new ObjectMapper().readValue(new ClassPathResource(path).getInputStream(), Map.class);
        } catch (IOException e) {
            log.error("Converting of json failed. Failed file path: {}", path);
            log.error(e.toString());
            throw new UncheckedIOException(e);
        }
    }
}
