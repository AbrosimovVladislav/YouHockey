package ru.yourhockey.web.controller;

import io.swagger.annotations.ApiOperation;
import ru.yourhockey.model.product_attributes.Type;

import java.util.List;

public interface TypeApi {

    @ApiOperation(value = "Get all types",
            notes = "Get all types",
            response = List.class)
    List<Type> types();

}
