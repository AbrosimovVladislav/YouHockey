package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.repo.ProductRepo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Product troubleTicketCreateProduct(Product product) {
        return productRepo.troubleTicketSaveOrUpdate(product);
    }

    public List<Product> search(String searchLine) {
        List<Product> products = productRepo.findAll();
        return products.stream()
                .map(p -> Pair.of(p, String.join(" ", List.of(p.getType().getShowName(), p.getBrand().getShortName(), p.getModel(), p.getAge()))))
                .filter(pair -> pair.getSecond().toUpperCase().contains(searchLine.toUpperCase()))
                .map(Pair::getFirst)
                .collect(Collectors.toList());
    }

    public Product update(Product product) {
        return productRepo.saveAndFlush(product);
    }
}
