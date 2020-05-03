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
public class ProductDto {
    /*@NonNull */private Long productId;
    /*@NonNull */private String menuItem;
    /*@Nullable*/ private String info;
    /*@NonNull */private String link;
    /*@NonNull */private String imageLink;
    /*@NonNull */private String model;
    /*@Nullable*/ private String params;
    /*@Nullable*/ private String color;
    private String condition;
    private String country;
    /*@NonNull */private Brand brand;
    /*@NonNull */private Rating rating;
    /*@NonNull */private Double minPrice;
}
