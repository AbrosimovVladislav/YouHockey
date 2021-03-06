package ru.yourhockey.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourhockey.model.MenuItem;
import ru.yourhockey.service.MenuService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuApiController implements MenuApi {

    private final MenuService menuService;

    @CrossOrigin
    @GetMapping("/menuItems")
    public List<MenuItem> menuItems() {
        return menuService.getMenuItems();
    }

}
