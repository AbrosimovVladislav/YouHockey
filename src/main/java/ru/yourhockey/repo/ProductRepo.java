package ru.yourhockey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.product_attributes.Brand;
import ru.yourhockey.model.product_attributes.Type;
import ru.yourhockey.repo.filterandsorting.FilterAndSortingRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepo extends JpaRepository<Product, Long>, FilterAndSortingRepository<Product> {
    default void saveOrUpdate(Product product) {
        if (product.getModel().isBlank()) return;
        findByBrandAndModelAndTypeAndSrcImageLink(
                product.getBrand(), product.getModel(), product.getType(), product.getSrcImageLink()
        )
                .ifPresentOrElse(
                        p -> save(product.setProductId(p.getProductId())),
                        () -> save(product)
                );
    }

    default Product troubleTicketSaveOrUpdate(Product product) {
        return findByBrandAndModelAndType(
                product.getBrand(), product.getModel(), product.getType())
                .map(value -> save(product.setProductId(value.getProductId())))
                .orElseGet(() -> save(product));
    }

    Optional<Product> findByBrandAndModelAndType(
            Brand brand, String model, Type type
    );

    Optional<Product> findByBrandAndModelAndTypeAndSrcImageLink(
            Brand brand, String model, Type type, String srcImageLink
    );

    @Query(nativeQuery = true, value = "SELECT * FROM product LIMIT ?1")
    List<Product> findAllLimit(int x);
}
