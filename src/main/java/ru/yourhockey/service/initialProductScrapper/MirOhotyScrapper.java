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
import org.springframework.stereotype.Component;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.product_attributes.Type;
import ru.yourhockey.repo.BrandRepo;
import ru.yourhockey.repo.CaliberRepo;
import ru.yourhockey.repo.PrincipleOfOperationRepo;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
@Component
@RequiredArgsConstructor
public class MirOhotyScrapper implements InitializingBean {

    private final BrandRepo brandRepo;
    private final CaliberRepo caliberRepo;
    private final PrincipleOfOperationRepo principleOfOperationRepo;

    Map<String, String> categories;

    @Override
    public void afterPropertiesSet() {
        categories = fromJson("yourhockeyold/static/product-scrapper/categories.json");
    }

    public List<Product> scrap() {
        List<Product> products = categories.entrySet().stream()
                .map(e -> category(e.getKey(), e.getValue()))
                .flatMap(List::stream)
                .collect(toList());

        return products;
    }

    public List<Product> category(String link, String category) {
        return null;
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

        Element element = doc.getElementsByClass("block-detail").get(0);
        Elements specifications = element.getElementsByClass("specifications").get(0)
                .getElementsByTag("tr");

        specifications.forEach(specificationLine -> {
            String text = specificationLine.text();
            if (text.contains("Модель")) {
                text = text.replaceAll("Модель: ", "");
                product.setModel(text);
            }
            if (text.contains("Серия")) {
                text = text.replaceAll("Серия: ", "");
                product.setModel(product.getModel() + " " + text);
            }
            if (text.contains("Бренд")) {
                text = text.replaceAll("Бренд: ", "");
                product.setBrand(brandRepo.findByShortName(text));
            }
            if (text.contains("Калибр")) {
                text = text.replaceAll("Калибр: ", "");
                product.setCaliber(caliberRepo.findByName(specificationLine.getElementsByClass("element_property_value").text()));
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
