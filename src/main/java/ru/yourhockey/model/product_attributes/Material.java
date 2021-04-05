package ru.yourhockey.model.product_attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import ru.yourhockey.model.product.Product;

import javax.persistence.*;
import java.util.Set;

import static ru.yourhockey.model.product_attributes.Material.MATERIAL_TABLE;

@Data
@Entity
@Table(name = MATERIAL_TABLE)
@ToString(exclude = "product")
public class Material {

    public static final String MATERIAL_ID = "materialId";
    public static final String MATERIAL_TABLE = "material";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = MATERIAL_ID, length = 8, nullable = false)
    private Long materialId;
    @JsonIgnore
    @OneToMany(mappedBy = MATERIAL_TABLE)
    private Set<Product> product;
}
