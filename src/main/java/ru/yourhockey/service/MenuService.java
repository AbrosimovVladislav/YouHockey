package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yourhockey.model.MenuItem;
import ru.yourhockey.model.product_attributes.Type;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final TypeService typeService;

    public List<MenuItem> getMenuItems() {
        List<Type> types = typeService.findAll();
        Set<String> uppers = new HashSet<>();
        Set<String> mediums = new HashSet<>();
        Set<String> lowers = new HashSet<>();
        Map<String, List<Long>> uppersIds = new HashMap<>();
        Map<String, List<Long>> mediumsIds = new HashMap<>();
        Map<String, List<Long>> lowersIds = new HashMap<>();

        types.forEach(t -> {
            uppers.add(t.getUpper());
            mediums.add(t.getMedium());
            if (t.getLower() != null) lowers.add(t.getLower());
        });

        fillIdMap(types, uppers, uppersIds, Type::getUpper);
        fillIdMap(types, mediums, mediumsIds, Type::getMedium);
        fillIdMap(types, lowers, lowersIds, Type::getLower);

        Map<String, Map<String, List<String>>> typeMap = new HashMap<>();
        for (Type type : types) {
            addTypeStringToNode(typeMap, type);
        }

        return typeMap.entrySet().stream()
                .map(e -> {
                    MenuItem menuItem = new MenuItem(e.getKey(), uppersIds.get(e.getKey()), e.getKey());
                    List<MenuItem> items = e.getValue().entrySet().stream()
                            .map(e2 -> {
                                MenuItem menuItem2 = new MenuItem(e2.getKey(), mediumsIds.get(e2.getKey()), e.getKey());
                                if (e2.getValue().get(0) != null) {
                                    menuItem2.setItems(e2.getValue().stream().map(e3 -> new MenuItem(e3, lowersIds.get(e3), e.getKey())).collect(Collectors.toList()));
                                }
                                return menuItem2;
                            }).collect(Collectors.toList());
                    menuItem.setItems(items);
                    return menuItem;
                }).sorted(Comparator.comparing(MenuItem::getLabel))
                .collect(Collectors.toList());
    }

    private void fillIdMap(List<Type> types,
                           Set<String> menuItems,
                           Map<String, List<Long>> menuItemIds,
                           Function<Type, String> methodRef) {
        menuItems.forEach(l -> types.forEach(t -> {
            if (methodRef.apply(t) != null)
                if (methodRef.apply(t).equalsIgnoreCase(l))
                    menuItemIds.merge(l, new ArrayList<>() {{
                        add(t.getTypeId());
                    }}, (a, b) -> {
                        a.addAll(b);
                        return a;
                    });
        }));
    }

    public void addTypeStringToNode(Map<String, Map<String, List<String>>> typeMap, Type t) {
        //Если такого аппера нет в списке
        if (typeMap.get(t.getUpper()) == null) {
            //Создаем для этого типа лоу и мед листы, которые суем по данному апперу
            List<String> lowList = new ArrayList<>();
            lowList.add(t.getLower());
            Map<String, List<String>> medList = new HashMap<>();
            medList.put(t.getMedium(), lowList);
            typeMap.put(t.getUpper(), medList);
        } else {
            //Если такого медиума еще нет
            if (typeMap.get(t.getUpper()).get(t.getMedium()) == null) {
                //Создаем лоулист который суем по данному медиуму
                List<String> lowList = new ArrayList<>();
                lowList.add(t.getLower());
                typeMap.get(t.getUpper()).put(t.getMedium(), lowList);
            } else {
                typeMap.get(t.getUpper()).get(t.getMedium()).add(t.getLower());
            }
        }
    }

}
