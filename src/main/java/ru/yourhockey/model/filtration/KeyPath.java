package ru.yourhockey.model.filtration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = KeyPath.KEY_PATH_TABLE)
@Getter
@Setter
public class KeyPath {

    public static final String KEY_PATH_TABLE = "keyPath";
    public static final String KEY_PATH_ID = "keyPathId";
    public static final String KEY_PATH_SOURCE = "source";
    public static final String KEY_PATH_TARGET_ENTITY = "targetEntity";
    public static final String KEY_PATH_TARGET_PARAM = "targetParam";
    public static final String KEY_PATH_VALUE = "value";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = KEY_PATH_ID, length = 8, nullable = false)
    private Long keyPathId;

    @Column(name = KEY_PATH_SOURCE)
    private String source;
    @Column(name = KEY_PATH_TARGET_ENTITY)
    private String targetEntity;
    @Column(name = KEY_PATH_TARGET_PARAM)
    private String targetParam;
    @Column(name = KEY_PATH_VALUE)
    private String value;

    @JsonIgnore
    @OneToMany(mappedBy = KEY_PATH_TABLE)
    private List<FilterItem> filterItem;

}
