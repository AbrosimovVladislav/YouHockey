package ru.yourhockey.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.product_attributes.Review;
import ru.yourhockey.service.ReviewService;
import ru.yourhockey.service.logging.MeasurePerformance;
import ru.yourhockey.web.dto.ReviewDto;
import ru.yourhockey.web.mapper.ReviewMapper;
import ru.yourhockey.web.preparer.FilterAndPageable;
import ru.yourhockey.web.preparer.Preparer;

import java.util.Collections;
import java.util.List;


@RestController
@MeasurePerformance
@RequiredArgsConstructor
public class ReviewApiController implements ReviewApi {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
    private final List<Preparer> preparers;

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @GetMapping(value = "/reviews/product/{productId}")
    public List<Review> getAllByProductId(@PathVariable String productId,
                                          @PageableDefault(size = DEFAULT_PAGE_SIZE, page = DEFAULT_PAGE_NUMBER)
                                                  Pageable pageable) {
        FilterAndPageable filterAndPageable = new FilterAndPageable(Collections.singletonMap("product.productId", productId), pageable);
        preparers.forEach(preparer -> preparer.prepare(filterAndPageable, Product.class));

        return reviewService.getAllByParameters(filterAndPageable.getFilter(),
                filterAndPageable.getPageable()
        );
    }

    @GetMapping(value = "/reviews/shop/{shopId}")
    public List<Review> getAllByShopId(@PathVariable String shopId,
                                       @PageableDefault(size = DEFAULT_PAGE_SIZE, page = DEFAULT_PAGE_NUMBER)
                                               Pageable pageable) {
        FilterAndPageable filterAndPageable = new FilterAndPageable(Collections.singletonMap("shop.shopId", shopId), pageable);
        preparers.forEach(preparer -> preparer.prepare(filterAndPageable, Product.class));

        return reviewService.getAllByParameters(filterAndPageable.getFilter(),
                filterAndPageable.getPageable()
        );
    }

    @PostMapping("/reviews")
    public ResponseEntity<Boolean> addReview(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.addReview(reviewMapper.map(reviewDto)));
    }
}
