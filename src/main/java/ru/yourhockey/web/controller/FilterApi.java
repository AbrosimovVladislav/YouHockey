package ru.yourhockey.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import ru.yourhockey.web.dto.FilterItemDto;

import java.util.List;

public interface FilterApi {

    @ApiOperation(value = "Get filter items by menu item",
            notes = "Get list of filter items by menu item",
            response = List.class)
    List<FilterItemDto> getFilterItemsByMenuItem(String menuItem);

}
