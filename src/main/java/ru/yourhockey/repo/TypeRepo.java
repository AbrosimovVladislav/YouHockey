package ru.yourhockey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourhockey.model.product_attributes.Type;

import java.util.Optional;

public interface TypeRepo extends JpaRepository<Type, Long> {
    Optional<Type> findByShowName(String showName);
}
