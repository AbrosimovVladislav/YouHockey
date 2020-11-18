package ru.yourhockey.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.service.initialProductScrapper.ImageLoader;
import ru.yourhockey.service.initialProductScrapper.ProductScrapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductScrapperTestController {

    private final ProductScrapper productScrapper;
    private final ImageLoader imageLoader;

    @GetMapping("/scrapProducts")
    @ApiOperation(value = "Init database with products from https://worldhockey.market",
            response = List.class)
    public List<Product> scrapProducts() {
        return productScrapper.actualizeFullProductCatalog();
    }

    @GetMapping("/getImages")
    @ApiOperation(value = "Init products images with images from https://worldhockey.market",
            response = List.class)
    public List<String> loadImages() {
        return imageLoader.loadImages();
    }

}
