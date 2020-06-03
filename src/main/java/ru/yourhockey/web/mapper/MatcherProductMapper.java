package ru.yourhockey.web.mapper;

import org.springframework.stereotype.Component;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.web.dto.MatcherProductDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MatcherProductMapper {

    public List<MatcherProductDto> mapList(List<Product> products) {
        return products.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private MatcherProductDto map(Product product) {
        return new MatcherProductDto()
                .setProductId(product.getProductId())
                .setModel(product.getModel())
                .setBrand(product.getBrand().getShortName())
                .setType(product.getType())
                .setAge(product.getAge().name())
                .setLink(product.getLink());
    }

}
