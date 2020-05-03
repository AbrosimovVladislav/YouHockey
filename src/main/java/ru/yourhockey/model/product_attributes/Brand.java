package ru.yourhockey.model.product_attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.yourhockey.model.product.Product;

import javax.persistence.*;
import java.util.Set;

import static ru.yourhockey.model.product_attributes.Brand.BRAND_TABLE;

@Data
@Entity
@Table(name = BRAND_TABLE)
public class Brand {
    public static final String BRAND_TABLE = "brand";
    public static final String BRAND_ID = "brand_id";
    public static final String BRAND_SHORT_NAME = "shortName";
    public static final String BRAND_FULL_NAME = "fullName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = BRAND_ID, length = 8, nullable = false)
    private Long brandId;

    @Column(name = BRAND_SHORT_NAME)
    private String shortName;

    @Column(name = BRAND_FULL_NAME)
    private String fullName;

    @JsonIgnore
    @OneToMany(mappedBy = BRAND_TABLE)
    private Set<Product> product;
}
