//package ru.yourhockey.service.task;
//
//import lombok.RequiredArgsConstructor;
//import ru.yourhockey.model.product.Product;
//import ru.yourhockey.model.product_attributes.Rating;
//import ru.yourhockey.model.product_attributes.Review;
//import ru.yourhockey.service.ProductService;
//
//@RequiredArgsConstructor
//public class CalculateProductRatingTask implements Runnable {
//    private final ProductService productService;
//    private final Product product;
//    private final Review review;
//    private final long reviewsCountBeforeUpdate;
//
//    @Override
//    public void run() {
//        int userMark = review.getMark();
//        if (product.getRating() == null) product.setRating(new Rating().setValue(0));
//        double currentRatingBeforeUpdate = product.getRating().getValue();
//        double currentRating = (currentRatingBeforeUpdate * reviewsCountBeforeUpdate + userMark) / (reviewsCountBeforeUpdate + 1);
//        product.getRating().setValue(currentRating);
//        product.setReviewCount(product.getReviewCount() + 1);
//        productService.update(product);
//    }
//}
