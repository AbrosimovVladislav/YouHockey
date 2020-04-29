package ru.yourhockey.model.product_attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ru.yourhockey.model.product.Product;

import javax.persistence.*;
import java.util.Set;

import static ru.yourhockey.model.product_attributes.Type.TYPE_TABLE;


@Entity
@Table(name = TYPE_TABLE)
@Getter
@Setter
public class Type {
    public static final String TYPE_TABLE = "type";
    public static final String TYPE_ID = "type_id";
    public static final String TYPE_NAME = "name";
    public static final String TYPE_UPPER = "upper";
    public static final String TYPE_MEDIUM = "medium";
    public static final String TYPE_LOWER = "lower";
    public static final String TYPE_MENU_ITEM = "menuItem";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TYPE_ID, length = 8, nullable = false)
    private Long typeId;

    @Column(name = TYPE_UPPER)
    private String upper;

    @Column(name = TYPE_MEDIUM)
    private String medium;

    @Column(name = TYPE_LOWER)
    private String lower;

    /**
     * Привязка к меню сучки
     */
    @Column(name = TYPE_MENU_ITEM)
    private String menuItem;

    @JsonIgnore
    @OneToMany(mappedBy = TYPE_TABLE)
    private Set<Product> product;
}
