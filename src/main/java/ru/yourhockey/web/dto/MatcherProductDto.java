package ru.yourhockey.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.yourhockey.model.product_attributes.Brand;
import ru.yourhockey.model.product_attributes.Rating;
import ru.yourhockey.model.product_attributes.Type;

@Setter
@Getter
@Accessors(chain = true)
public class MatcherProductDto {
    /*@NonNull */private Long productId;
    /*@NonNull */private String productType;
    /*@NonNull */private String link;
    /*@NonNull */private String model;
    /*@NonNull */private String brand;
    /*@NonNull */private String type;
}
