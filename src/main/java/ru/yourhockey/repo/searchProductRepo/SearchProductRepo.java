package ru.yourhockey.repo.searchProductRepo;

import ru.yourhockey.model.product.Product;

import java.util.List;

public interface SearchProductRepo {

    List<Product> searchByQuery(String query);

}
