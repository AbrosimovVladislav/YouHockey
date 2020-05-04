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
                .setModel(product.getModel())
                .setBrand(product.getBrand())
                .setType(product.getType())
                .setAge(product.getAge())
                .setDescription(product.getDescription())
                .setCharacteristics(product.getCharacteristics())
                .setLink(product.getLink())
                .setImageLink(product.getImageLink())
                .setRating(product.getRating())
                .setMinPrice(offerService.calculateMinPriceByProduct(product.getProductId()));
    }
}
