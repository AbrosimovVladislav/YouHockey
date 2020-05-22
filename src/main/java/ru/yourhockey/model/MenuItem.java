package ru.yourhockey.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MenuItem {
    String label;
    String url;
    List<String> routerLink;
    Map<String, String> queryParams;
    List<MenuItem> items;

    String nativeName;

    public MenuItem(String nativeName, List<Long> ids, String menuItemUpper) {
        this.label = nativeName;
        this.url = "product";
        this.routerLink = List.of("product/" + nativeName);
        this.queryParams = Map.of("typeId", ids.stream().sorted().map(Object::toString).collect(Collectors.joining(",")), "menuItem", menuItemUpper);
    }
}
