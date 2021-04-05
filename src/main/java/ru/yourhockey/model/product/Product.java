package ru.yourhockey.model.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import ru.yourhockey.model.BasicEntity;
import ru.yourhockey.model.offer.Offer;
import ru.yourhockey.model.product_attributes.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

import static ru.yourhockey.model.product.Product.PRODUCT_TABLE;
import static ru.yourhockey.model.product_attributes.Brand.BRAND_ID;
import static ru.yourhockey.model.product_attributes.Caliber.CALIBER_ID;
import static ru.yourhockey.model.product_attributes.ChargeType.CHARGE_TYPE_ID;
import static ru.yourhockey.model.product_attributes.Color.COLOR_ID;
import static ru.yourhockey.model.product_attributes.Magnification.MAGNIFICATION_ID;
import static ru.yourhockey.model.product_attributes.Material.MATERIAL_ID;
import static ru.yourhockey.model.product_attributes.MountType.MOUNT_TYPE_ID;
import static ru.yourhockey.model.product_attributes.NumberOfShot.NUMBER_OF_SHOT_ID;
import static ru.yourhockey.model.product_attributes.PrincipleOfOperation.PRINCIPLE_OF_OPERATION_ID;
import static ru.yourhockey.model.product_attributes.ScrewThread.SCREW_THREAD_ID;
import static ru.yourhockey.model.product_attributes.TuningKind.TUNING_KIND_ID;
import static ru.yourhockey.model.product_attributes.Type.TYPE_ID;


@Entity
@Table(name = PRODUCT_TABLE)
@Accessors(chain = true)
@Getter
@Setter
@ToString(exclude = "offer")
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

    @ManyToOne
    @JoinColumn(name = CALIBER_ID)
    private Caliber caliber;

    @ManyToOne
    @JoinColumn(name = PRINCIPLE_OF_OPERATION_ID)
    private PrincipleOfOperation principleOfOperation;

    @ManyToOne
    @JoinColumn(name = CHARGE_TYPE_ID)
    private ChargeType chargeType;

    @ManyToOne
    @JoinColumn(name = NUMBER_OF_SHOT_ID)
    private NumberOfShot numberOfShot;

    @ManyToOne
    @JoinColumn(name = TUNING_KIND_ID)
    private TuningKind tuningKind;

    @ManyToOne
    @JoinColumn(name = COLOR_ID)
    private Color color;

    @ManyToOne
    @JoinColumn(name = MATERIAL_ID)
    private Material material;

    @ManyToOne
    @JoinColumn(name = SCREW_THREAD_ID)
    private ScrewThread screwThread;

    @ManyToOne
    @JoinColumn(name = MOUNT_TYPE_ID)
    private MountType mountType;

    @ManyToOne
    @JoinColumn(name = MAGNIFICATION_ID)
    private Magnification magnification;

    private String weight;

    private String barrelLength;

    private String bulletMass;

    @Deprecated(since = "Продумать систему совместимости")
    private String compatibility;

    private String capacity;

    private String length;
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
}
