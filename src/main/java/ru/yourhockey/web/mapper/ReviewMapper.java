package ru.yourhockey.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.product_attributes.Review;
import ru.yourhockey.model.shop.Shop;
import ru.yourhockey.service.ProductService;
import ru.yourhockey.service.ShopService;
import ru.yourhockey.web.dto.ReviewDto;


@Component
@RequiredArgsConstructor
public class ReviewMapper {
    private final ShopService shopService;
    private final ProductService productService;

    public Review map(ReviewDto reviewDto) {
        Shop shop = reviewDto.getShopId() == null ? null : shopService.getById(reviewDto.getShopId());
        Product product = reviewDto.getProductId() == null ? null : productService.getById(reviewDto.getProductId());
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
