package ru.yourhockey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourhockey.model.product_attributes.Caliber;

public interface CaliberRepo extends JpaRepository<Caliber, Long> {
    public Caliber findByName(String name);
}
