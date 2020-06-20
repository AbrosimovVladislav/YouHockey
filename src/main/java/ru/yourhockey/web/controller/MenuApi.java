package ru.yourhockey.web.controller;

import io.swagger.annotations.ApiOperation;
import ru.yourhockey.model.MenuItem;

import java.util.List;

public interface MenuApi {

    @ApiOperation(value = "Get menu items",
            notes = "Get list of actual menu items",
            response = List.class)
    List<MenuItem> menuItems();

}
