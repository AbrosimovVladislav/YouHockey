package ru.yourhockey.model.product_attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.yourhockey.model.product.Product;

import javax.persistence.*;
import java.util.Set;

import static ru.yourhockey.model.product_attributes.ScrewThread.SCREW_THREAD_TABLE;

@Data
@Entity
@Table(name = SCREW_THREAD_TABLE)
public class ScrewThread {

    public static final String SCREW_THREAD_ID = "screwThreadId";
    public static final String SCREW_THREAD_TABLE = "screwThread";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = SCREW_THREAD_ID, length = 8, nullable = false)
    private Long screwThreadId;

    @JsonIgnore
    @OneToMany(mappedBy = SCREW_THREAD_TABLE)
    private Set<Product> product;
}
