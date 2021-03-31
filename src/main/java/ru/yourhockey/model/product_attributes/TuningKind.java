package ru.yourhockey.model.product_attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.yourhockey.model.product.Product;

import javax.persistence.*;
import java.util.Set;

import static ru.yourhockey.model.product_attributes.TuningKind.TUNING_KIND_TABLE;

@Data
@Entity
@Table(name = TUNING_KIND_TABLE)
public class TuningKind {

    public static final String TUNING_KIND_ID = "tuningKindId";
    public static final String TUNING_KIND_TABLE = "tuningKind";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TUNING_KIND_ID, length = 8, nullable = false)
    private Long tuningKindId;

    @JsonIgnore
    @OneToMany(mappedBy = TUNING_KIND_TABLE)
    private Set<Product> product;
}
