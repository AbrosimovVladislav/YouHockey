package ru.yourhockey.model.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import ru.yourhockey.model.BasicEntity;
import ru.yourhockey.model.offer.Offer;
import ru.yourhockey.model.product_attributes.Rating;
import ru.yourhockey.model.product_attributes.Review;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = Shop.SHOP_TABLE)
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Shop implements BasicEntity {
    public static final String SHOP_TABLE = "shop";
    public static final String SHOP_ID = "shopId";
    public static final String SHOP_NAME = "name";
    public static final String SHOP_ADDRESS = "address";
    public static final String SHOP_WEBSITE = "website";
    public static final String SHOP_INFO = "shopInfo";
    public static final String SHOP_RATING = "shopRating";
    public static final String SHOP_RATING_ID = "ratingId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = SHOP_ID, length = 8, nullable = false)
    private Long shopId;

    @Column(name = SHOP_NAME, nullable = false)
    private String name;

    @Column(name = SHOP_WEBSITE, unique = true)
    private String website;

    @Column(name = SHOP_INFO, length = 2000)
    private String shopInfo;

    @JsonIgnore
    @OneToMany(mappedBy = SHOP_TABLE)
    private Set<Offer> product;

    @JsonIgnore
    @OneToMany(mappedBy = SHOP_TABLE)
    private Set<Review> review;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = SHOP_RATING_ID, referencedColumnName = SHOP_RATING_ID)
    private Rating rating;
}
