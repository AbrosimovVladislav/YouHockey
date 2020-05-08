package ru.yourhockey.web.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.yourhockey.model.product_attributes.Type;


@Data
@Accessors(chain = true)
public class OfferDto {
    private Long productId;
    private String shopName;
    private Double price;
    private Boolean inStore;
    private String link;
    private String name;
    private String brand;
    private Type type;
}
