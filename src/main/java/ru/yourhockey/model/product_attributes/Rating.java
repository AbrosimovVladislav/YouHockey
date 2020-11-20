//package ru.yourhockey.model.product_attributes;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.experimental.Accessors;
//import ru.yourhockey.model.product.Product;
//import ru.yourhockey.model.shop.Shop;
//
//import javax.persistence.*;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//
//
//@Entity
//@Table(name = Rating.RATING_TABLE)
//@Getter
//@Setter
//@Accessors(chain = true)
//public class Rating {
//    public static final String RATING_TABLE = "rating";
//    public static final String RATING_ID = "ratingId";
//    public static final String RATING_VALUE_SORT = "rating.value";
//    public static final String RATING_PRODUCT = "product";
//    private static final String RATING_PRODUCT_ID = "productId";
//    private static final String RATING_VALUE = "value";
//    private static final String RATING_SHOP_ID = "shopId";
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = RATING_ID, length = 8, nullable = false)
//    private Long ratingId;
//
//    @Min(0)
//    @Max(5)
//    @Column(name = RATING_VALUE)
//    private double value;
//
//    @JsonIgnore
//    @OneToOne(mappedBy = RATING_TABLE)
//    private Product product;
//
//    @JsonIgnore
//    @OneToOne(mappedBy = RATING_TABLE)
//    private Shop shop;
//}
