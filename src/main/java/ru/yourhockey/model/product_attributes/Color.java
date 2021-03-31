package ru.yourhockey.model.product_attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.yourhockey.model.product.Product;

import javax.persistence.*;
import java.util.Set;

import static ru.yourhockey.model.product_attributes.Color.COLOR_TABLE;

@Data
@Entity
@Table(name = COLOR_TABLE)
public class Color {

    public static final String COLOR_ID = "colorId";
    public static final String COLOR_TABLE = "color";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLOR_ID, length = 8, nullable = false)
    private Long colorId;

    @JsonIgnore
    @OneToMany(mappedBy = COLOR_TABLE)
    private Set<Product> product;
}
