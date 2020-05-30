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

    public List<Offer> getAllByParameters(Map<String, String> requestParams, Pageable pageable) {
        return offerRepo.findAllByParameters(requestParams, pageable, Offer.class);
    }

    public List<Offer> saveAll(List<Offer> offers){
        return offerRepo.saveAll(offers);
    }

    public void deleteAll(){
        offerRepo.deleteAll();
    }
}
