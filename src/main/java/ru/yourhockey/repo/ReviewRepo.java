package ru.yourhockey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourhockey.model.product_attributes.Review;
import ru.yourhockey.model.shop.Shop;
import ru.yourhockey.repo.filterandsorting.FilterAndSortingRepository;


public interface ReviewRepo extends JpaRepository<Review, Long>, FilterAndSortingRepository<Review> {
    long countReviewByShop(Shop shop);
}
