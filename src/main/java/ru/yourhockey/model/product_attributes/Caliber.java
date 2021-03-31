package ru.yourhockey.model.product_attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.yourhockey.model.product.Product;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@Table(name = Caliber.CALIBER_TABLE)
public class Caliber {

    public static final String CALIBER_ID = "caliberId";
    public static final String CALIBER_TABLE = "caliber";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CALIBER_ID, length = 8, nullable = false)
    private Long caliberId;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = CALIBER_TABLE)
    private Set<Product> product;

}
