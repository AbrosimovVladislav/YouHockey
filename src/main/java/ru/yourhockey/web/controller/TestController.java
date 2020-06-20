package ru.yourhockey.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.yourhockey.repo.ProductRepo;
import ru.yourhockey.web.dto.ProductDto;
import ru.yourhockey.web.mapper.ProductMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    @CrossOrigin
    @GetMapping("/test/{x}")
    public List<ProductDto> test(@PathVariable int x) {
        return productMapper.mapList(productRepo.findAllLimit(x));
    }

}
