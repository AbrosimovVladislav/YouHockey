package ru.yourhockey.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yourhockey.model.offer.Offer;
import ru.yourhockey.service.OfferService;
import ru.yourhockey.service.ProductService;
import ru.yourhockey.service.logging.MeasurePerformance;
import ru.yourhockey.web.dto.OfferDto;
import ru.yourhockey.web.mapper.OfferMapper;
import ru.yourhockey.web.preparer.FilterAndPageable;
import ru.yourhockey.web.preparer.Preparer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OfferApiController implements OfferApi {

    private final OfferService offerService;
    private final OfferMapper offerMapper;
    private final ProductService productService;
    private final List<Preparer> preparers;

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @MeasurePerformance
    @GetMapping(value = "/offers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Offer> getAllByParams(@RequestParam Map<String, String> requestParams,
                                      @PageableDefault(size = DEFAULT_PAGE_SIZE, page = DEFAULT_PAGE_NUMBER)
                                              Pageable pageable) {

        FilterAndPageable filterAndPageable = new FilterAndPageable(requestParams, pageable);
        preparers.forEach(preparer -> preparer.prepare(filterAndPageable, Offer.class));

        return offerService.getAllByParameters(filterAndPageable.getFilter(),
                filterAndPageable.getPageable()
        );
    }

    @CrossOrigin
    @MeasurePerformance
    @GetMapping(value = "/offers/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Offer> getByProductId(@PathVariable String productId,
                                      @PageableDefault(size = DEFAULT_PAGE_SIZE, page = DEFAULT_PAGE_NUMBER)
                                              Pageable pageable) {
        FilterAndPageable filterAndPageable = new FilterAndPageable(new HashMap<>(Map.of("product.productId", productId)), pageable);
        preparers.forEach(preparer -> preparer.prepare(filterAndPageable, Offer.class));

        filterAndPageable.getFilter().put("inStock", "true");
        return offerService.getAllByParameters(filterAndPageable.getFilter(),
                filterAndPageable.getPageable()
        );
    }


    //ToDo Investigate поучему сохраняется меньше чем приходит
    //  private final OfferRepo offerRepo;
    //  List<Offer> incomingCopy = new ArrayList<>(offers);
    //  List<Offer> inDb = offerRepo.findAll();
    //  incomingCopy.removeAll(inDb);
    //  System.out.println(incomingCopy);
    //  List<Offer> savedOffers = offerService.saveAll(incomingCopy);
    @MeasurePerformance
    @PostMapping(value = "/offers")
    public ResponseEntity<List<OfferDto>> receiveFinalOffers(@RequestBody List<OfferDto> body) {
        log.info("Number of final offers incoming request :{}", body.size());
        List<Offer> offers = offerMapper.mapToOffer(body);
        offerService.deleteAll();
        List<Offer> savedOffers = offerService.saveAll(offers);
        log.info("Number of offers saved to db :{}", savedOffers.size());
        productService.recalculateMinMaxPrice();
        return ResponseEntity.ok(offerMapper.mapToOfferDto(savedOffers));
    }

}
