package ru.yourhockey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourhockey.model.shop.Shop;

public interface ShopRepo extends JpaRepository<Shop, Long> {
}
