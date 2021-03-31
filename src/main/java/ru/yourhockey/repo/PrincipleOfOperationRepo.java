package ru.yourhockey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourhockey.model.product_attributes.PrincipleOfOperation;

public interface PrincipleOfOperationRepo extends JpaRepository<PrincipleOfOperation, Long> {
    PrincipleOfOperation findByName(String name);
}
