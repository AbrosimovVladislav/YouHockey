package ru.yourhockey.model.product_attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import ru.yourhockey.model.product.Product;

import javax.persistence.*;
import java.util.Set;

import static ru.yourhockey.model.product_attributes.ChargeType.CHARGE_TYPE_TABLE;

@Data
@Entity
@Table(name = CHARGE_TYPE_TABLE)
@ToString(exclude = "product")
public class ChargeType {

    public static final String CHARGE_TYPE_ID = "chargeTypeId";
    public static final String CHARGE_TYPE_TABLE = "chargeType";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CHARGE_TYPE_ID, length = 8, nullable = false)
    private Long chargeTypeId;

    @JsonIgnore
    @OneToMany(mappedBy = CHARGE_TYPE_TABLE)
    private Set<Product> product;
}
