package ru.yourhockey.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.service.ProductService;
import ru.yourhockey.web.dto.ProductDto;
import ru.yourhockey.web.mapper.ProductMapper;
import ru.yourhockey.web.validation.RequestParamsValidator;
import ru.yourhockey.web.webentities.FilterAndPageable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final RequestParamsValidator validator;
    private final ProductMapper productMapper;

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ProductDto> getAllByParams(@RequestParam Map<String, String> requestParams,
                                           @PageableDefault(size = DEFAULT_PAGE_SIZE, page = DEFAULT_PAGE_NUMBER)
                                                   Pageable pageable) {
        FilterAndPageable pairOfParamsAndPageable = validator.validate(requestParams, pageable, Product.class);
        List<Product> products = productService.getAllByParameters(
                pairOfParamsAndPageable.getFilter(),
                pairOfParamsAndPageable.getPageable()
        );
        return products.stream().map(productMapper::map).collect(Collectors.toList());
    }

    @GetMapping(value = "/products/{productId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ProductDto getById(@PathVariable long productId) {
        Product product = productService.getById(productId);
        return productMapper.map(product);
    }

    @GetMapping(value = "/product/search/{searchQuery}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> search(@PathVariable String searchQuery) {
        return productService.search(searchQuery);
    }
}
