package ru.yourhockey.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.yourhockey.model.BasicEntity;
import ru.yourhockey.model.offer.Offer;
import ru.yourhockey.model.product_attributes.Brand;
import ru.yourhockey.model.product_attributes.Rating;
import ru.yourhockey.model.product_attributes.Review;

import javax.persistence.*;
import java.util.Set;

import static ru.yourhockey.model.product.Product.PRODUCT_TABLE;
import static ru.yourhockey.model.product_attributes.Brand.BRAND_ID;


@Entity
@Table(name = PRODUCT_TABLE)
@Getter
@Setter
public class Product implements BasicEntity {
    public static final String PRODUCT_TABLE = "product";
    public static final String PRODUCT_MODEL = "model";
    public static final String PRODUCT_ID = "productId";
    public static final String PRODUCT_DTYPE = "dtype";
    public static final String PRODUCT_INFO = "info";
    public static final String PRODUCT_LINK = "link";
    public static final String PRODUCT_IMAGE_LINK = "imageLink";
    public static final String GUN_CAPACITY = "capacity";
    public static final String GUN_TOTAL_LENGTH = "totalLength";
    public static final String GUN_BARREL_LENGTH = "barrelLength";
    public static final String PART_COLOR = "color";
    public static final String PART_PARAMS = "params";
    public static final String PRODUCT_TYPE = "productType";
    public static final String PRODUCT_RATING_ID = "ratingId";
    public static final String PRODUCT_OPERATING_PRINCIPLE = "operatingPrinciple";
    public static final String PRODUCT_CONDITION = "condition";
    public static final String PRODUCT_BARREL_ORIENTATION = "barrelOrientation";
    public static final String PRODUCT_COUNTRY = "country";
    public static final String AMMO_SLEEVE_MATERIAL = "sleeveMaterial";
    public static final String AMMO_CHARGE_TYPE = "chargeType";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PRODUCT_ID, length = 8, nullable = false)
    private Long productId;

    @Column(name = PRODUCT_TYPE, nullable = false, updatable = false)
    private String menuItem;

    @Column(name = PRODUCT_INFO)
    private String info;

    @Column(name = PRODUCT_LINK)
    private String link;

    @Column(name = PRODUCT_IMAGE_LINK)
    private String imageLink;

    @Column(name = PRODUCT_MODEL)
    private String model;

    @Column(name = PART_PARAMS)
    private String params;

    @Column(name = PRODUCT_COUNTRY)
    private String country;

    @Column(name = PART_COLOR)
    private String color;

    @Column(name = PRODUCT_CONDITION)
    private String condition;

    @ManyToOne
    @JoinColumn(name = BRAND_ID, nullable = false)
    private Brand brand;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = PRODUCT_RATING_ID, referencedColumnName = PRODUCT_RATING_ID, unique = true)
    private Rating rating;

    @OneToMany(mappedBy = PRODUCT_TABLE)
    private Set<Offer> productInShop;

    @JsonIgnore
    @OneToMany(mappedBy = PRODUCT_TABLE)
    private Set<Review> review;
}
