package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.repo.ProductRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ProductRepo productRepo;

    public Optional<byte[]> getProductImage(long productId) {
        return productRepo.findById(productId)
                .map(Product::getImageLink)
                .map(Paths::get)
                .map(p -> {
                    try {
                        return Files.readAllBytes(p);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                });
    }
}
