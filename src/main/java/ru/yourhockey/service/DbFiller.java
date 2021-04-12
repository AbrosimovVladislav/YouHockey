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
        menuItems.add("ОХОТНИЧЬЕ_ОРУЖИЕ");

        menuItems.stream()
                .peek(mi -> filterItemService.createFilterItem(new FilterItem().setMenuItem(mi).setName("Цена").setType(FilterItem.FilterType.RANGE).setKeyPath(keyPathService.getById(1L)).setRank(1)))
                .peek(mi -> filterItemService.createFilterItem(new FilterItem().setMenuItem(mi).setName("Брэнд").setType(FilterItem.FilterType.CHECKBOX).setKeyPath(keyPathService.getById(2L)).setRank(2)))
                .peek(mi -> filterItemService.createFilterItem(new FilterItem().setMenuItem(mi).setName("Калибр").setType(FilterItem.FilterType.CHECKBOX).setKeyPath(keyPathService.getById(3L)).setRank(3)))
                .peek(mi -> filterItemService.createFilterItem(new FilterItem().setMenuItem(mi).setName("Масса оружия").setType(FilterItem.FilterType.RANGE).setKeyPath(keyPathService.getById(4L)).setRank(4)))
                .peek(mi -> filterItemService.createFilterItem(new FilterItem().setMenuItem(mi).setName("Длина ствола").setType(FilterItem.FilterType.RANGE).setKeyPath(keyPathService.getById(5L)).setRank(5)))
                .forEach(mi -> filterItemService.createFilterItem(new FilterItem().setMenuItem(mi).setName("Принцип работы").setType(FilterItem.FilterType.CHECKBOX).setKeyPath(keyPathService.getById(6L)).setRank(6)));
    }

    private void createKeyPaths() {
        String productSource = "product";
        keyPaths.add(new KeyPath().setKeyPathId(1L).setSource(productSource).setTargetEntity("offer").setTargetParam("price").setValue("offer.price"));
        keyPaths.add(new KeyPath().setKeyPathId(2L).setSource(productSource).setTargetEntity("brand").setTargetParam("short_name").setValue("brand.shortName"));
        keyPaths.add(new KeyPath().setKeyPathId(3L).setSource(productSource).setTargetEntity("caliber").setTargetParam("name").setValue("caliber.name"));
        keyPaths.add(new KeyPath().setKeyPathId(4L).setSource(productSource).setTargetEntity(productSource).setTargetParam("weight").setValue("weight"));
        keyPaths.add(new KeyPath().setKeyPathId(5L).setSource(productSource).setTargetEntity(productSource).setTargetParam("barrel_length").setValue("barrelLength"));
        keyPaths.add(new KeyPath().setKeyPathId(6L).setSource(productSource).setTargetEntity("principle_of_operation").setTargetParam("name").setValue("principleOfOperation.name"));
        keyPaths.add(new KeyPath().setKeyPathId(7L).setSource(productSource).setTargetEntity("charge_type").setTargetParam("name").setValue("chargeType.name"));
        keyPaths.forEach(keyPathService::create);
    }
}
