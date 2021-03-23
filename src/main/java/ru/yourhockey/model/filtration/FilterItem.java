package ru.yourhockey.model.filtration;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.yourhockey.model.BasicEntity;

import javax.persistence.*;
import java.util.List;

import static ru.yourhockey.model.filtration.FilterItem.FILTER_ITEM_TABLE;
import static ru.yourhockey.model.filtration.KeyPath.KEY_PATH_ID;

@Entity
@Table(name = FILTER_ITEM_TABLE)
@Getter
@Setter
@Accessors(chain = true)
public class FilterItem implements BasicEntity {

    public static final String FILTER_ITEM_TABLE = "filterItem";
    public static final String FILTER_ITEM_ID = "filterItemId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = FILTER_ITEM_ID, length = 8, nullable = false)
    private Long filterItemId;

    private String name;
    @ManyToOne
    @JoinColumn(name = KEY_PATH_ID, nullable = false)
    private KeyPath keyPath;
    @Enumerated(EnumType.STRING)
    private FilterType type;
    private String menuItem;
    private int rank;

    /**
     * For different filter types :
     * range -> min and max
     * checkbox -> list of values
     */
    @Transient
    List<String> values;

    public enum FilterType {
        RANGE, CHECKBOX
    }

}
