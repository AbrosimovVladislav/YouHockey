package ru.yourhockey.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourhockey.model.product_attributes.Brand;
import ru.yourhockey.service.BrandService;
import ru.yourhockey.service.logging.MeasurePerformance;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandApiController implements BrandApi {

    private final BrandService brandService;

    @CrossOrigin
    @GetMapping("/brands")
    @MeasurePerformance
    public List<Brand> brands() {
        return brandService.getAll();
    }

}
