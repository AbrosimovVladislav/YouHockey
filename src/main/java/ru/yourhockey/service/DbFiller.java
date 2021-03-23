package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yourhockey.model.filtration.FilterItem;
import ru.yourhockey.model.filtration.KeyPath;

import java.util.List;

@Component
@Deprecated
@Profile("!test")
@RequiredArgsConstructor
public class DbFiller implements InitializingBean {

    private final FilterItemService filterItemService;
    private final KeyPathService keyPathService;

    private final List<KeyPath> keyPaths;
    private final List<String> menuItems;


    @Override
    public void afterPropertiesSet() {
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
        String productSource = "product";
        keyPaths.add(new KeyPath().setKeyPathId(1L).setSource(productSource).setTargetEntity("offer").setTargetParam("price").setValue("offer.price"));
        keyPaths.add(new KeyPath().setKeyPathId(2L).setSource(productSource).setTargetEntity("brand").setTargetParam("short_name").setValue("brand.shortName"));
        keyPaths.add(new KeyPath().setKeyPathId(3L).setSource(productSource).setTargetEntity(productSource).setTargetParam("age").setValue("age"));
        keyPaths.forEach(keyPathService::create);
    }
}
