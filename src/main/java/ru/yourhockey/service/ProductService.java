package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.repo.ProductRepo;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public List<Product> getAllByParameters(Map<String, String> requestParams, Pageable pageable) {
        return productRepo.findAllByParameters(requestParams, pageable, Product.class);
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Product getById(long id) {
        return productRepo.getOne(id);
    }

    public List<Product> search(String query) {
        return productRepo.searchByQuery(query);
    }
}
