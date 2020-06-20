package ru.yourhockey.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import ru.yourhockey.web.dto.MatcherProductDto;
import ru.yourhockey.web.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductApi {

    @ApiOperation(value = "Get products dto by parameters",
            notes = "Get products dto by input parameters via query builder",
            response = List.class)
    List<ProductDto> getAllByParams(Map<String, String> requestParams, Pageable pageable);

    @ApiOperation(value = "Create new product and save ti db",
            notes = "Create new product from input body",
            response = Long.class)
    Long createProduct(Map<String, String> body);

    @ApiOperation(value = "Get all matcher products dto",
            notes = "Get all matcher products dto",
            response = ResponseEntity.class)
    ResponseEntity<List<MatcherProductDto>> getAll();

    @ApiOperation(value = "Get product dto by id",
            notes = "Get product dto by id",
            response = ProductDto.class)
    ProductDto getById(long productId);

    @ApiOperation(value = "Get products dto by search line",
            notes = "Get products dto by search line",
            response = ResponseEntity.class)
    ResponseEntity<List<ProductDto>> search(String searchLine);


}
