package ru.yourhockey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.product_attributes.Age;
import ru.yourhockey.model.product_attributes.Brand;
import ru.yourhockey.model.product_attributes.Type;
import ru.yourhockey.repo.filterandsorting.FilterAndSortingRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepo extends JpaRepository<Product, Long>, FilterAndSortingRepository<Product> {
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

    default Product troubleTicketSaveOrUpdate(Product product) {
        return findByBrandAndModelAndAgeAndType(
                product.getBrand(), product.getModel(), product.getAge(), product.getType())
                .map(value -> save(product.setProductId(value.getProductId())))
//                .orElseGet(() -> save(product));
                .orElseGet(() -> {
                    saveInCircumventOfSequence2(product);
                    return product;
                });
    }

    default void saveInCircumventOfSequence2(Product product){
        specialSave(product.getProductId(),
                product.getAge().name(),
                product.getCharacteristics(),
                product.getDescription(),
                product.getImageLink(),
                product.getLink(),
                product.getModel(),
                product.getReviewCount(),
                product.getSrcImageLink(),
                product.getBrand().getBrandId(),
                product.getType().getTypeId());
    }

    Optional<Product> findByBrandAndModelAndAgeAndType(
            Brand brand, String model, Age age, Type type
    );

    Optional<Product> findByBrandAndModelAndAgeAndTypeAndSrcImageLink(
            Brand brand, String model, Age age, Type type, String srcImageLink
    );

    @Query(nativeQuery = true, value = "INSERT INTO product (product_id, age, characteristics, description, image_link, link, model, review_count, src_image_link, brand_id, type_id) VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11)")
    void specialSave(Long product_id, String age, String characteristics, String description, String image_link, String link, String model, Integer review_count, String src_image_link, Long brand_id, Long type_id);

    @Query(nativeQuery = true, value = "SELECT max(product_id) FROM product")
    Long getMaxProductId();

    @Query(nativeQuery = true, value = "SELECT * FROM product LIMIT ?1")
    List<Product> findAllLimit(int x);
}
