package ru.yourhockey.model.product_attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.yourhockey.model.BasicEntity;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.shop.Shop;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static ru.yourhockey.model.product.Product.PRODUCT_ID;
import static ru.yourhockey.model.product_attributes.Review.REVIEW_TABLE;
import static ru.yourhockey.model.shop.Shop.SHOP_ID;

@Entity
@Table(name = REVIEW_TABLE)
@Getter
@Setter
@Accessors(chain = true)
public class Review implements BasicEntity {

    public static final String REVIEW_TABLE = "review";
    public static final String REVIEW_ID = "reviewId";
    public static final String REVIEW_MARK = "mark";
    public static final String REVIEW_COMMENT = "comment";
    public static final String REVIEW_PROS = "pros";
    public static final String REVIEW_CONS = "cons";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = REVIEW_ID, length = 8, nullable = false)
    private Long reviewId;

    @Min(1)
    @Max(5)
    @Column(name = REVIEW_MARK, nullable = false)
    private int mark;

    @Column(name = REVIEW_PROS)
    private String pros;

    @Column(name = REVIEW_CONS)
    private String cons;

    @Column(name = REVIEW_COMMENT)
    private String comment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = SHOP_ID)
    private Shop shop;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = PRODUCT_ID)
    private Product product;

    @Column(name = "user_id")
    private Long user;
}
