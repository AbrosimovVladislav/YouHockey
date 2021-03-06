package ru.yourhockey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.yourhockey.model.offer.Offer;
import ru.yourhockey.repo.filterandsorting.FilterAndSortingRepository;

public interface OfferRepo extends JpaRepository<Offer, Long>, FilterAndSortingRepository<Offer> {
    @Query(value = "SELECT MIN(price) FROM offer WHERE product_id = :productId AND in_stock = true", nativeQuery = true)
    Double calculateMinPriceByProduct(Long productId);

    @Query(value = "SELECT MAX(price) FROM offer WHERE product_id = :productId AND in_stock = true", nativeQuery = true)
    Double calculateMaxPriceByProduct(Long productId);
}
