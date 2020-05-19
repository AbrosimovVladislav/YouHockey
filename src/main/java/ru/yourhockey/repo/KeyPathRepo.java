package ru.yourhockey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourhockey.model.filtration.KeyPath;

public interface KeyPathRepo extends JpaRepository<KeyPath, Long> {
}
