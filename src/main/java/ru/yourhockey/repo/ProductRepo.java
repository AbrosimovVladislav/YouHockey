package ru.yourhockey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.repo.filterandsorting.FilterAndSortingRepository;
import ru.yourhockey.repo.searchProductRepo.SearchProductRepo;


public interface ProductRepo extends JpaRepository<Product, Long>, FilterAndSortingRepository<Product>, SearchProductRepo {
}
