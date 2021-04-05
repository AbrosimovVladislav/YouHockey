package ru.yourhockey.model.product_attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import ru.yourhockey.model.product.Product;

import javax.persistence.*;
import java.util.Set;

import static ru.yourhockey.model.product_attributes.Magnification.MAGNIFICATION_TABLE;

@Data
@Entity
@Table(name = MAGNIFICATION_TABLE)
@ToString(exclude = "product")
public class Magnification {

    public static final String MAGNIFICATION_ID = "magnificationId";
    public static final String MAGNIFICATION_TABLE = "magnification";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = MAGNIFICATION_ID, length = 8, nullable = false)
    private Long magnificationId;

    @JsonIgnore
    @OneToMany(mappedBy = MAGNIFICATION_TABLE)
    private Set<Product> product;
}
