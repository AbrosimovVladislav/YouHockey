package ru.yourhockey.model.product_attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.yourhockey.model.product.Product;

import javax.persistence.*;
import java.util.Set;

import static ru.yourhockey.model.product_attributes.PrincipleOfOperation.PRINCIPLE_OF_OPERATION_TABLE;

@Data
@Entity
@Table(name = PRINCIPLE_OF_OPERATION_TABLE)
public class PrincipleOfOperation {

    public static final String PRINCIPLE_OF_OPERATION_ID = "principleOfOperationId";
    public static final String PRINCIPLE_OF_OPERATION_TABLE = "principleOfOperation";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PRINCIPLE_OF_OPERATION_ID, length = 8, nullable = false)
    private Long principleOfOperationId;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = PRINCIPLE_OF_OPERATION_TABLE)
    private Set<Product> product;
}
