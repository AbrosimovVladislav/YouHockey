package ru.yourhockey.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.yourhockey.model.BasicEntity;
import ru.yourhockey.model.offer.Offer;
import ru.yourhockey.model.product_attributes.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.yourhockey.model.product.Product.PRODUCT_TABLE;
import static ru.yourhockey.model.product_attributes.Brand.BRAND_ID;
import static ru.yourhockey.model.product_attributes.Type.TYPE_ID;


@Entity
@Table(name = PRODUCT_TABLE)
@Accessors(chain = true)
@Getter
@Setter
public class Product implements BasicEntity {
    public static final String PRODUCT_TABLE = "product";
    public static final String PRODUCT_ID = "productId";
    public static final String PRODUCT_MODEL = "model";
    public static final String PRODUCT_AGE = "age";
    public static final String PRODUCT_DESCRIPTION = "description";
    public static final String PRODUCT_CHARACTERISTICS = "characteristics";
    public static final String PRODUCT_LINK = "link";
    private static final String PRODUCT_SRC_IMAGE_LINK = "srcImageLink";
    public static final String PRODUCT_IMAGE_LINK = "imageLink";
    public static final String PRODUCT_RATING_ID = "ratingId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PRODUCT_ID, length = 8, nullable = false)
    private Long productId;

    /**
     * Name of product. EX. 'Vapor 2X PRO'
     */
    @Column(name = PRODUCT_MODEL)
    private String model;

    /**
     * Brand of product. EX. 'BAUER'
     */
    @ManyToOne
    @JoinColumn(name = BRAND_ID, nullable = false)
    private Brand brand;

    /**
     * Complex type of product. EX. 'Upper:КОНЬКИ Medium:КОНЬКИХОККЕЙНЫЕ Lower:null ShowName: Коньки хоккейные'
     */
    @ManyToOne
    @JoinColumn(name = TYPE_ID, nullable = false)
    private Type type;

    /**
     * Age category of product. EX. 'SR' Possible:(YTH,JR,INT,SR)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = PRODUCT_AGE)
    private Age age;

    /**
     * Description of product. EX. 'Почувствуйте анатомическую форму нового налокотника Supreme 1S благодаря комп...'
     */
    @Column(name = PRODUCT_DESCRIPTION, length = 4095)
    private String description;

    /**
     * List of characteristics of product. EX. 'Общая посадка:Анатомическая форма, Локтевой сустав: FleXorb® G-FOR...'
     */
    @Column(name = PRODUCT_CHARACTERISTICS)
    private String characteristics;

    /**
     * Technical fields
     */
    @Column(name = PRODUCT_LINK)
    private String link;

    @Column(name = PRODUCT_SRC_IMAGE_LINK)
    private String srcImageLink;

    @Column(name = PRODUCT_IMAGE_LINK)
    private String imageLink;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = PRODUCT_RATING_ID, referencedColumnName = PRODUCT_RATING_ID, unique = true)
    private Rating rating;

    @OneToMany(mappedBy = PRODUCT_TABLE)
    private Set<Offer> offer = new HashSet<>();

    private Double minPrice;

    @JsonIgnore
    @OneToMany(mappedBy = PRODUCT_TABLE)
    private Set<Review> review = new HashSet<>();

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer reviewCount = 0;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", model='" + model + '\'' +
                ", brand=" + (brand != null ? brand.getShortName() : "null") +
                ", type=" + (type != null ? type.getShowName() : "null") +
                ", age='" + age + '\'' +
                ", description='" + description + '\'' +
                ", characteristics='" + characteristics + '\'' +
                ", link='" + link + '\'' +
                ", srcImageLink='" + srcImageLink + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", rating=" + (rating != null ? rating.getValue() : "null") +
                ", minPrice=" + minPrice +
                ", review=" + review.stream().map(Review::getMark).collect(Collectors.toList()) +
                ", reviewCount=" + reviewCount +
                '}';
    }
}
