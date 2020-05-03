package ru.yourhockey.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.service.OfferService;
import ru.yourhockey.web.dto.ProductDto;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final OfferService offerService;

    public ProductDto map(Product product) {
        return new ProductDto().setProductId(product.getProductId())
                .setMenuItem(product.getMenuItem())
                .setInfo(product.getInfo())
                .setLink(product.getLink())
                .setImageLink(product.getImageLink())
                .setModel(product.getModel())
                .setParams(product.getParams())
                .setColor(product.getColor())
                .setCondition(product.getCondition())
                .setCountry(product.getCountry())
                .setBrand(product.getBrand())
                .setRating(product.getRating())
                .setMinPrice(offerService.calculateMinPriceByProduct(product.getProductId()));
    }
}
