package ru.yourhockey.model.product_attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import ru.yourhockey.model.product.Product;

import javax.persistence.*;
import java.util.Set;

import static ru.yourhockey.model.product_attributes.MountType.MOUNT_TYPE_TABLE;

@Data
@Entity
@Table(name = MOUNT_TYPE_TABLE)
@ToString(exclude = "product")
public class MountType {

    public static final String MOUNT_TYPE_ID = "mountTypeId";
    public static final String MOUNT_TYPE_TABLE = "mountType";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = MOUNT_TYPE_ID, length = 8, nullable = false)
    private Long mountTypeId;

    @JsonIgnore
    @OneToMany(mappedBy = MOUNT_TYPE_TABLE)
    private Set<Product> product;
}
