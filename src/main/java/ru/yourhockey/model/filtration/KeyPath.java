package ru.yourhockey.model.filtration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = KeyPath.KEY_PATH_TABLE)
@Getter
@Setter
@Accessors(chain = true)
public class KeyPath {

    public static final String KEY_PATH_TABLE = "keyPath";
    public static final String KEY_PATH_ID = "keyPathId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = KEY_PATH_ID, length = 8, nullable = false)
    private Long keyPathId;

    private String source;
    private String targetEntity;
    private String targetParam;
    private String value;

    @JsonIgnore
    @OneToMany(mappedBy = KEY_PATH_TABLE)
    private List<FilterItem> filterItem;

}
