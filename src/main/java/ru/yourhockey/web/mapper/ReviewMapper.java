package ru.yourhockey.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.product_attributes.Review;
import ru.yourhockey.model.shop.Shop;
import ru.yourhockey.service.ProductService;
import ru.yourhockey.service.ShopService;
import ru.yourhockey.web.dto.ReviewDto;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class ReviewMapper {
    private final ShopService shopService;
    private final ProductService productService;

    public Review map(ReviewDto reviewDto) {
        Shop shop = Optional.ofNullable(reviewDto.getShopId()).map(shopService::getById).orElse(null);
        Product product = Optional.ofNullable(reviewDto.getProductId()).map(productService::getById).orElse(null);
        return new Review()
                .setMark(reviewDto.getMark())
                .setPros(reviewDto.getPros())
                .setCons(reviewDto.getCons())
                .setComment(reviewDto.getComment())
                .setShop(shop)
                .setProduct(product)
                .setUser(reviewDto.getUserId());
    }
}
