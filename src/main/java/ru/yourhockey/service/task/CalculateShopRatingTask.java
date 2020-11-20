//package ru.yourhockey.service.task;
//
//import lombok.RequiredArgsConstructor;
//import ru.yourhockey.model.product_attributes.Rating;
//import ru.yourhockey.model.product_attributes.Review;
//import ru.yourhockey.model.shop.Shop;
//import ru.yourhockey.service.ShopService;
//
//
//@RequiredArgsConstructor
//public class CalculateShopRatingTask implements Runnable {
//    private final ShopService shopService;
//    private final Shop shop;
//    private final Review review;
//    private final long reviewsCountBeforeUpdate;
//
//    @Override
//    public void run() {
//        int userMark = review.getMark();
//        if (shop.getRating() == null) shop.setRating(new Rating().setValue(0));
//        double currentRatingBeforeUpdate = shop.getRating().getValue();
//        double currentRating = (currentRatingBeforeUpdate * reviewsCountBeforeUpdate + userMark) / (reviewsCountBeforeUpdate + 1);
//        shop.getRating().setValue(currentRating);
//        shopService.update(shop);
//    }
//}
