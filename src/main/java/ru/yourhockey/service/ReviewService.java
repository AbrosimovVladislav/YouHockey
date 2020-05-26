package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.product_attributes.Review;
import ru.yourhockey.model.shop.Shop;
import ru.yourhockey.repo.ReviewRepo;
import ru.yourhockey.service.task.CalculateProductRatingTask;
import ru.yourhockey.service.task.CalculateShopRatingTask;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepo reviewRepo;
    private final ShopService shopService;
    private final ProductService productService;

    public List<Review> getAllByParameters(Map<String, String> requestParams, Pageable pageable) {
        return reviewRepo.findAllByParameters(requestParams, pageable, Review.class);
    }

    @Transactional
    public boolean addReview(Review review) {
        if (review.getProduct() == null) {
            Shop shop = review.getShop();
            long reviewsCountBeforeUpdate = reviewRepo.countReviewByShop(shop);
            new CalculateShopRatingTask(shopService, shop, review, reviewsCountBeforeUpdate).run();
        } else {
            Product product = review.getProduct();
            long reviewsCountBeforeUpdate = reviewRepo.countReviewByProduct(product);
            new CalculateProductRatingTask(productService, product, review, reviewsCountBeforeUpdate).run();
        }
        reviewRepo.save(review);
        return true;
    }
}
