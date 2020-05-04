package ru.yourhockey.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yourhockey.model.offer.Offer;
import ru.yourhockey.service.ProductService;
import ru.yourhockey.service.ShopService;
import ru.yourhockey.web.dto.OfferDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OfferMapper {

    private final ProductService productService;
    private final ShopService shopService;

    public List<Offer> mapToOffer(List<OfferDto> offerDtos) {
        return offerDtos.stream()
                .map(this::mapToOffer)
                .collect(Collectors.toList());
    }

    public List<OfferDto> mapToOfferDto(List<Offer> offers) {
        return offers.stream()
                .map(this::mapToOfferDto)
                .collect(Collectors.toList());
    }

    private OfferDto mapToOfferDto(Offer offer) {
        return new OfferDto()
                .setName(offer.getProduct().getModel())
                .setBrand(offer.getProduct().getBrand().getShortName())
                .setPrice(offer.getPrice())
                .setInStore(offer.isInStock())
                .setType(offer.getProduct().getType())
                .setShopName(offer.getShop().getName())
                .setLink(offer.getLink())
                .setProductId(offer.getProduct().getProductId());
    }

    private Offer mapToOffer(OfferDto offerDto) {
        return new Offer(productService.getById(offerDto.getProductId()),
                shopService.getByName(offerDto.getShopName()),
                offerDto.getPrice(),
                offerDto.getInStore(),
                offerDto.getLink(),
                0); //ToDo popularity is on 0 !!!
    }
}
