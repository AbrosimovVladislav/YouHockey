package ru.yourhockey.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import ru.yourhockey.model.offer.Offer;
import ru.yourhockey.web.dto.OfferDto;

import java.util.List;
import java.util.Map;

public interface OfferApi {

    @ApiOperation(value = "Get offers by parameters",
            notes = "Get offers by input parameters via query builder",
            response = List.class)
    List<Offer> getAllByParams(Map<String, String> requestParams, Pageable pageable);

    @ApiOperation(value = "Get offers by product id",
            notes = "Get offers by product id",
            response = List.class)
    List<Offer> getByProductId(String productId, Pageable pageable);

    @ApiOperation(value = "Receive final offers",
            notes = "Receive final offers from matcher service, delete old offers, save new offers, recalculate min and max prices for products",
            response = ResponseEntity.class)
    ResponseEntity<List<OfferDto>> receiveFinalOffers(List<OfferDto> body);

}
