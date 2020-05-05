package ru.yourhockey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourhockey.model.product_attributes.Brand;

public interface BrandRepo extends JpaRepository<Brand, Long> {
    Brand findByShortName(String shortName);
}
