package ru.yourhockey.model.product_attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.yourhockey.model.product.Product;

import javax.persistence.*;
import java.util.Set;

import static ru.yourhockey.model.product_attributes.NumberOfShot.NUMBER_OF_SHOT_TABLE;

@Data
@Entity
@Table(name = NUMBER_OF_SHOT_TABLE)
public class NumberOfShot {

    public static final String NUMBER_OF_SHOT_ID = "numberOfShotId";
    public static final String NUMBER_OF_SHOT_TABLE = "numberOfShot";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = NUMBER_OF_SHOT_ID, length = 8, nullable = false)
    private Long numberOfShotId;

    @JsonIgnore
    @OneToMany(mappedBy = NUMBER_OF_SHOT_TABLE)
    private Set<Product> product;
}
