package ru.yourhockey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourhockey.model.product_attributes.Type;

public interface TypeRepo extends JpaRepository<Type, Long> {
}
