package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import ru.yourhockey.model.filtration.FilterItem;
import ru.yourhockey.model.filtration.KeyPath;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DbFiller implements InitializingBean {

    private final FilterItemService filterItemService;
    private final KeyPathService keyPathService;

    private final List<KeyPath> keyPaths;
    private final List<String> menuItems;


    @Override
    public void afterPropertiesSet() throws Exception {
        createKeyPaths();
        createFilterTypes();
    }

    private void createFilterTypes() {
        menuItems.add("КОНЬКИ");
        menuItems.add("ЗАЩИТА_ИГРОКА");
        menuItems.add("КЛЮШКИ");
        menuItems.add("ОДЕЖДА");
        menuItems.add("ВРАТАРСКАЯ_ФОРМА");
        menuItems.add("СУМКИ");
        menuItems.add("АКСЕССУАРЫ");
        menuItems.add("РАЗНОЕ");
        menuItems.stream()
                .peek(mi -> filterItemService.createFilterItem(new FilterItem().setMenuItem(mi).setName("Цена").setType(FilterItem.FilterType.RANGE).setKeyPath(keyPathService.getById(1L)).setRank(1)))
                .peek(mi -> filterItemService.createFilterItem(new FilterItem().setMenuItem(mi).setName("Брэнд").setType(FilterItem.FilterType.CHECKBOX).setKeyPath(keyPathService.getById(2L)).setRank(2)))
                .forEach(mi -> filterItemService.createFilterItem(new FilterItem().setMenuItem(mi).setName("Возраст").setType(FilterItem.FilterType.CHECKBOX).setKeyPath(keyPathService.getById(3L)).setRank(3)));
    }

    private void createKeyPaths() {
/*
        PRICE
        BRAND
        TYPE ???
        AGE
*/
        String productSource = "product";
        keyPaths.add(new KeyPath().setKeyPathId(1L).setSource(productSource).setTargetEntity("offer").setTargetParam("price").setValue("offer.price"));
        keyPaths.add(new KeyPath().setKeyPathId(2L).setSource(productSource).setTargetEntity("brand").setTargetParam("short_name").setValue("brand.shortName"));
        keyPaths.add(new KeyPath().setKeyPathId(3L).setSource(productSource).setTargetEntity(productSource).setTargetParam("age").setValue("age"));
        keyPaths.forEach(keyPathService::create);
    }
}


/*-- ----------------------------------------------------------------1 PRICE---------------------------------------------------------
INSERT INTO key_path (source, target_entity, target_param, value) VALUES ('product','offer','price','offer.price')ON CONFLICT (value) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (1,'КОНЬКИ','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (2,'ЗАЩИТА_ИГРОКА','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (3,'КЛЮШКИ','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (4,'ОДЕЖДА','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (5,'ВРАТАРСКАЯ_ФОРМА','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (6,'СУМКИ','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (7,'АКСЕССУАРЫ','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;
INSERT INTO filter_item (filter_item_id, menu_item, name, type, key_path_id, rank) VALUES (8,'РАЗНОЕ','Цена', 'RANGE', 1,1) ON CONFLICT (filter_item_id) DO NOTHING;;*/
