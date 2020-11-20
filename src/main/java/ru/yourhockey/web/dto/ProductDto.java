package ru.yourhockey.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.yourhockey.model.product_attributes.Brand;
import ru.yourhockey.model.product_attributes.Type;


@Setter
@Getter
@Accessors(chain = true)
public class ProductDto {
    private Long productId;
    private String model;
    private Brand brand;
    private Type type;
    private String age;
    private String description;
    private String characteristics;
    private String link;
    private String imageLink;
    //    private Rating rating;
    private Double minPrice;
    private Double maxPrice;
    private Integer offerQuantity;
}
