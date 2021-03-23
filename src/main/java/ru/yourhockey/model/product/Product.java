package ru.yourhockey.model.product;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.yourhockey.model.BasicEntity;
import ru.yourhockey.model.offer.Offer;
import ru.yourhockey.model.product_attributes.Brand;
import ru.yourhockey.model.product_attributes.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

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
    public static final String PRODUCT_MIN_PRICE = "minPrice";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PRODUCT_ID, length = 8, nullable = false)
    private Long productId;

    /**
     * Name of product. EX. 'Vapor 2X PRO'
     */
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
     * Description of product. EX. 'Почувствуйте анатомическую форму нового налокотника Supreme 1S благодаря комп...'
     */
    @Column(length = 4095)
    private String description;

    /**
     * List of characteristics of product. EX. 'Общая посадка:Анатомическая форма, Локтевой сустав: FleXorb® G-FOR...'
     */
    private String characteristics;

    /**
     * Technical fields
     */
    private String link;

    private String srcImageLink;

    private String imageLink;

    @OneToMany(mappedBy = PRODUCT_TABLE)
    private Set<Offer> offer = new HashSet<>();

    @Min(value = 0, message = "Product minPrice cannot be lower than 0")
    private Double minPrice;
    private Double maxPrice;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer reviewCount = 0;

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", model='" + model + '\'' +
                ", brand=" + (brand != null ? brand.getShortName() : "null") +
                ", type=" + (type != null ? type.getShowName() : "null") +
                ", description='" + description + '\'' +
                ", characteristics='" + characteristics + '\'' +
                ", link='" + link + '\'' +
                ", srcImageLink='" + srcImageLink + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", reviewCount=" + reviewCount +
                '}';
    }
}
