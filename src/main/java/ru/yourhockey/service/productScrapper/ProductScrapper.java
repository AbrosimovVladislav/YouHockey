package ru.yourhockey.service.productScrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.product_attributes.Type;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductScrapper implements InitializingBean {

    Map<String, String> categories;

    @Override
    public void afterPropertiesSet() throws Exception {
        categories = fromJson("src/main/resources/product-scrapper/categories.json");
    }

    public List<Product> actualizeFullProductCatalog(){
        return categories.entrySet().stream()
                .map(entry -> category(entry.getKey(), convertToType(entry.getValue())))
                .flatMap(List::stream)
                .collect(Collectors.toList());

    }

    public List<Product> category (String url, Type type){

        return List.of();//ToDo implement
    }

    private Type convertToType(String name){

        return null;//ToDo implement
    }

    private static Map<String, String> fromJson(String path) {
        try {
            return new ObjectMapper().readValue(Paths.get(path).toFile(), Map.class);
        } catch (IOException e) {
            log.error("Converting of json failed. Failed file path: {}", path);
            log.error(e.toString());
            throw new UncheckedIOException(e);
        }
    }
}
