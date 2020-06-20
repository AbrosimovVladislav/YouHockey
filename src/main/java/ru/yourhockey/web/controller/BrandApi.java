package ru.yourhockey.web.controller;

import io.swagger.annotations.ApiOperation;
import ru.yourhockey.model.product_attributes.Brand;

import java.util.List;

public interface BrandApi {

    @ApiOperation(value = "Get brands",
            notes = "Get all brands from db",
            response = List.class)
    List<Brand> brands();

}
