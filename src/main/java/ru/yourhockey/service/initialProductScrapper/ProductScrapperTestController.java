package ru.yourhockey.service.initialProductScrapper;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourhockey.model.product.Product;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductScrapperTestController {

    private final ProductScrapper productScrapper;
    private final ImageLoader imageLoader;

    @GetMapping("/scrapProducts")
    public List<Product> scrapProducts() {
        return productScrapper.actualizeFullProductCatalog();
    }

    @GetMapping("/getImages")
    public List<String> loadImages() {
        return imageLoader.loadImages();
    }

}
