package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.yourhockey.model.offer.Offer;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.repo.OfferRepo;
import ru.yourhockey.repo.ProductRepo;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepo offerRepo;
    private final ProductRepo productRepo;

    public List<Offer> getAllByParameters(Map<String, String> requestParams, Pageable pageable) {
        return offerRepo.findAllByParameters(requestParams, pageable, Offer.class);
    }

    public Double calculateMinPriceByProduct(Long productId) {
        long s1 = System.currentTimeMillis();
        Double productMinPrice = offerRepo.calculateMinPriceByProduct(productId);
        long e1 = System.currentTimeMillis();
        log.info("Time to calculateMinPrice: {}", e1 - s1);

        long s2 = System.currentTimeMillis();
        Product pr = productRepo.getOne(productId).setMinPrice(productMinPrice);
        long e2 = System.currentTimeMillis();
        log.info("Time to fetch product by id: {}", e2 - s2);

        long s3 = System.currentTimeMillis();
        productRepo.save(pr);
        long e3 = System.currentTimeMillis();
        log.info("Time to save update product: {}", e3 - s3);

        return productMinPrice;
    }

    public List<Offer> saveAll(List<Offer> offers){
        return offerRepo.saveAll(offers);
    }

    public void deleteAll(){
        offerRepo.deleteAll();
    }
}
