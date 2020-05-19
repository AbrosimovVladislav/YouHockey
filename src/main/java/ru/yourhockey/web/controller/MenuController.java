package ru.yourhockey.web.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourhockey.model.product_attributes.Type;
import ru.yourhockey.service.TypeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final TypeService typeService;

    @GetMapping("/menuItems")
    public List<MenuItem> menuItems() {
        return convertTypesToMenuItems();
    }

    public List<MenuItem> convertTypesToMenuItems() {
        List<Type> types = typeService.findAll();

        Map<String, Map<String, List<String>>> typeMap = new HashMap<>();
        for (Type type : types) {
            addTypeStringToNode(typeMap, type);
        }

        return typeMap.entrySet().stream()
                .map(e -> {
                    MenuItem menuItem = new MenuItem();
                    menuItem.setUrl(e.getKey());
                    List<MenuItem> items = e.getValue().entrySet().stream()
                            .map(e2 -> {
                                MenuItem menuItem2 = new MenuItem();
                                menuItem2.setUrl(e2.getKey());
                                menuItem2.setItems(e2.getValue().stream().map(MenuItem::new).collect(Collectors.toList()));
                                return menuItem2;
                            }).collect(Collectors.toList());
                    menuItem.setItems(items);
                    return menuItem;
                }).collect(Collectors.toList());
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

    @Data
    @NoArgsConstructor
    static class MenuItem {
        String url;
        List<MenuItem> items;

        public MenuItem(String url) {
            this.url = url;
        }

    }

}
