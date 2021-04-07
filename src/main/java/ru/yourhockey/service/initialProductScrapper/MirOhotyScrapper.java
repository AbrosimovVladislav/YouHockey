package ru.yourhockey.service.initialProductScrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.product_attributes.Caliber;
import ru.yourhockey.model.product_attributes.Type;
import ru.yourhockey.repo.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

@Slf4j
@Component
@RequiredArgsConstructor
public class MirOhotyScrapper implements InitializingBean {

    private final ProductRepo productRepo;
    private final BrandRepo brandRepo;
    private final CaliberRepo caliberRepo;
    private final PrincipleOfOperationRepo principleOfOperationRepo;
    private final TypeRepo typeRepo;

    Map<String, String> categories;
    Map<String, Integer> pageNumbers;
    List<Caliber> availableCalibers;

    @Override
    public void afterPropertiesSet() {
        categories = fromJson("product-scrapper/categories.json");

        pageNumbers = fromJson("product-scrapper/pageCount.json").entrySet().stream()
                .map(e -> Pair.of(e.getKey(), parseInt(e.getValue())))
                .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));

        availableCalibers = caliberRepo.findAll();
    }

    public List<Product> scrap() {
        List<Product> products = categories.entrySet().stream()
                .map(e -> category(e.getKey(), typeRepo.findByShowName(e.getValue()).get()))
                .flatMap(List::stream)
                .collect(toList());

        return products;
    }

    public int numberOfPages(String categoryLink) {
        return pageNumbers.getOrDefault(categoryLink, 1);
    }

    public List<Product> category(String link, Type type) {

        int numberOfPages = numberOfPages(link);

        return IntStream.range(1, numberOfPages + 1)
                .mapToObj(i -> link + "?PAGEN_1=" + i)
                .map(categoryPageLink -> categoryPage(categoryPageLink, type))
                .flatMap(List::stream)
                .collect(toList());
    }

    public List<Product> categoryPage(String categoryPageLink, Type type) {
        Document doc;
        try {
            doc = Jsoup.connect(categoryPageLink).get();
        } catch (IOException e) {
            log.error("Failed to get product page with url {}", categoryPageLink);
            throw new RuntimeException("Failed to get product page with url " + categoryPageLink);
        }

        List<String> productLinks = doc.getElementsByClass("cards js-product-view table-view").get(0)
                .getElementsByClass("middle-block").stream()
                .map(e -> e.getElementsByTag("a").get(0).attr("href"))
                .map(e -> "https://www.huntworld.ru" + e)
                .collect(Collectors.toUnmodifiableList());

        List<Product> products = productLinks.stream()
                .map(prodLink -> productPage(prodLink, type))
                .collect(Collectors.toUnmodifiableList());

        return products;
    }

    public Product productPage(String url, Type type) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error("Failed to get product page with url {}", url);
            throw new RuntimeException("Failed to get product page with url " + url);
        }

        Product product = new Product();

        String model = doc.getElementsByClass("item-head").get(0).getElementsByTag("h1").get(0).text();
        product.setModel(model);

        String imageLink = "https://www.huntworld.ru/" + doc.getElementsByClass("swiper-wrapper").get(0).getElementsByTag("img").get(0).attr("src");
        imageLink = imageLink.replaceAll("320_320_1", "720_720_1");
        product.setSrcImageLink(imageLink);

        Element element = doc.getElementsByClass("block-detail").get(0);
        Elements specifications = element.getElementsByClass("specifications").get(0)
                .getElementsByTag("tr");

        specifications.forEach(specificationLine -> {
            String text = specificationLine.text();
            if (text.contains("Бренд")) {
                text = text.replaceAll("Бренд: ", "");
                product.setBrand(brandRepo.findByShortName(text));
            }
            if (text.contains("Калибр")) {
                text = text.replaceAll("Калибр: ", "");
                Caliber caliber = caliberRepo.findByName(specificationLine.getElementsByClass("element_property_value").text());
                if (caliber == null) {
                    Optional<Caliber> optCaliber = availableCalibers.stream()
                            .filter(currCaliber -> {
                                Optional<String> caliberName = Arrays.stream(model.split(" "))
                                        .filter(modelPart -> modelPart.equals(currCaliber.getName()))
                                        .findFirst();
                                return caliberName.isPresent();
                            }).findFirst();
                    if (optCaliber.isPresent()) {
                        caliber = optCaliber.get();
                    }
                }
                product.setCaliber(caliber);
            }
            if (text.contains("Вес")) {
                text = text.replaceAll("[^0-9]", "");
                product.setWeight(text);
            }
            if (text.contains("Длина ствола")) {
                text = text.replaceAll("[^0-9]", "");
                product.setBarrelLength(text);
            }
            if (text.contains("Принцип")) {
                product.setPrincipleOfOperation(principleOfOperationRepo.findByName(specificationLine.getElementsByClass("element_property_value").text()));
            }
        });

        product.setType(type);

        try {
            productRepo.saveOrUpdate(product);
            log.info("Saving " + product);
        } catch (Exception e) {
            log.error("Something wrong with savind to db --- " + e.getMessage());
        }

        return product;
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
