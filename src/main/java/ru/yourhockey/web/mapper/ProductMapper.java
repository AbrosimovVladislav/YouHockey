package ru.yourhockey.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.service.OfferService;
import ru.yourhockey.web.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final OfferService offerService;

    public List<ProductDto> mapList(List<Product> products) {
        return products.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public ProductDto map(Product product) {
        return new ProductDto().setProductId(product.getProductId())
                .setModel(product.getModel())
                .setBrand(product.getBrand())
                .setType(product.getType())
                .setDescription(product.getDescription())
                .setCharacteristics(product.getCharacteristics())
                .setLink(product.getLink())
                .setImageLink(product.getImageLink())
//                .setRating(product.getRating())
                .setMinPrice(product.getMinPrice())
                .setMaxPrice(product.getMaxPrice())
                .setOfferQuantity(product.getOffer().size());
    }
}
