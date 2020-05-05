package ru.yourhockey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.product_attributes.Brand;
import ru.yourhockey.model.product_attributes.Type;
import ru.yourhockey.repo.filterandsorting.FilterAndSortingRepository;
import ru.yourhockey.repo.searchProductRepo.SearchProductRepo;

import java.util.Optional;


public interface ProductRepo extends JpaRepository<Product, Long>, FilterAndSortingRepository<Product>, SearchProductRepo {
    default void saveOrUpdate(Product product) {
        if (product.getModel().isBlank()) return;
        findByBrandAndModelAndAgeAndTypeAndSrcImageLink(
                product.getBrand(), product.getModel(), product.getAge(), product.getType(), product.getSrcImageLink()
        )
                .ifPresentOrElse(
                        p -> save(product.setProductId(p.getProductId())),
                        () -> save(product)
                );
    }

    Optional<Product> findByBrandAndModelAndAgeAndTypeAndSrcImageLink(
            Brand brand, String model, String age, Type type, String srcImageLink
    );
}
