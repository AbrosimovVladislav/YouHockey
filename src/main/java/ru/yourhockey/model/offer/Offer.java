package ru.yourhockey.model.offer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.yourhockey.model.BasicEntity;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.shop.Shop;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static ru.yourhockey.model.product.Product.PRODUCT_ID;
import static ru.yourhockey.model.shop.Shop.SHOP_ID;

@Data
@Entity
@Table(name = Offer.OFFER_TABLE)
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Offer implements BasicEntity {
    public static final String OFFER_ID = "offerId";
    public static final String OFFER_TABLE = "offer";
    public static final String OFFER_PRICE = "price";
    public static final String OFFER_SALE = "sale";
    public static final String OFFER_IN_STOCK = "inStock";
    public static final String OFFER_ADDITIONAL_INFO = "additionalInfo";
    public static final String OFFER_LINK = "link";
    public static final String OFFER_POPULARITY = "popularity";

    @Id
    @Column(name = OFFER_ID, nullable = false)
    private String productInShopId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = PRODUCT_ID, nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = SHOP_ID, nullable = false)
    private Shop shop;

    @Column(name = OFFER_PRICE, nullable = false)
    private double price;

    @Column(name = OFFER_IN_STOCK, nullable = false)
    private boolean inStock;

    @Column(name = OFFER_LINK, nullable = false)
    private String link;

    @Min(0)
    @Max(1)
    @Column(name = OFFER_POPULARITY, nullable = false)
    private double popularity;

    @SuppressWarnings("unused")
    public Offer(Product product, Shop shop, double price, boolean inStock, String link, double popularity) {
        this.productInShopId = product.getProductId() + ":" + shop.getShopId();
        this.product = product;
        this.shop = shop;
        this.price = price;
        this.inStock = inStock;
        this.link = link;
        this.popularity = popularity;
    }
}
