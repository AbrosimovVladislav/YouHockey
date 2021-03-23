package ru.yourhockey.model.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import ru.yourhockey.model.BasicEntity;
import ru.yourhockey.model.offer.Offer;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = Shop.SHOP_TABLE)
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Shop implements BasicEntity {
    public static final String SHOP_TABLE = "shop";
    public static final String SHOP_ID = "shopId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = SHOP_ID, length = 8, nullable = false)
    private Long shopId;

    private String name;
    private String website;
    private String shopInfo;

    @JsonIgnore
    @OneToMany(mappedBy = SHOP_TABLE)
    private Set<Offer> product;
}
