package ru.yourhockey.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import ru.yourhockey.model.product_attributes.Review;
import ru.yourhockey.web.dto.ReviewDto;

import java.util.List;

public interface ReviewApi {

    @ApiOperation(value = "Get reviews by product id",
            notes = "Get reviews by product id",
            response = List.class)
    List<Review> getAllByProductId(String productId, Pageable pageable);

    @ApiOperation(value = "Get reviews by shop id",
            notes = "Get reviews by shop id",
            response = List.class)
    List<Review> getAllByShopId(String shopId, Pageable pageable);

    @ApiOperation(value = "Create new review and save to db",
            notes = "Create new review and save to db",
            response = ResponseEntity.class)
    ResponseEntity<Boolean> addReview(ReviewDto reviewDto);

}
