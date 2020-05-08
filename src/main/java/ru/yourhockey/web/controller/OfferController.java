package ru.yourhockey.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yourhockey.model.offer.Offer;
import ru.yourhockey.service.OfferService;
import ru.yourhockey.web.dto.OfferDto;
import ru.yourhockey.web.mapper.OfferMapper;
import ru.yourhockey.web.validation.RequestParamsValidator;
import ru.yourhockey.web.webentities.FilterAndPageable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;
    private final RequestParamsValidator validator;
    private final OfferMapper offerMapper;

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @GetMapping(value = "/offers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Offer> getAllByParams(@RequestParam Map<String, String> requestParams,
                                      @PageableDefault(size = DEFAULT_PAGE_SIZE, page = DEFAULT_PAGE_NUMBER)
                                              Pageable pageable) {
        FilterAndPageable pairOfParamsAndPageable = validator.validate(requestParams, pageable, Offer.class);
        return offerService.getAllByParameters(pairOfParamsAndPageable.getFilter(),
                pairOfParamsAndPageable.getPageable()
        );
    }

    @GetMapping(value = "/offers/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Offer> getByProductId(@PathVariable String productId,
                                      @PageableDefault(size = DEFAULT_PAGE_SIZE, page = DEFAULT_PAGE_NUMBER)
                                              Pageable pageable) {
        FilterAndPageable pairOfParamsAndPageable = validator.validate(Collections.singletonMap("product.productId", productId), pageable, Offer.class);
        return offerService.getAllByParameters(pairOfParamsAndPageable.getFilter(),
                pairOfParamsAndPageable.getPageable()
        );
    }

    @PostMapping(value = "/offers")
    public ResponseEntity<List<OfferDto>> receiveFinalOffers(@RequestBody List<OfferDto> body) {
        List<Offer> offers = offerMapper.mapToOffer(body);
        offerService.deleteAll();
        List<Offer> savedOffers = offerService.saveAll(offers);
        return ResponseEntity.ok(offerMapper.mapToOfferDto(savedOffers));
    }

}
