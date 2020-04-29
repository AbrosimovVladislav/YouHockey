package ru.yourhockey.repo.searchProductRepo;

import io.gunmarket.demo.marketApp.model.domain.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SearchProductRepoImpl implements SearchProductRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Product> searchByQuery(String query) {
        String resultQuery = "%"+query+"%";
        return (List<Product>) entityManager.createQuery("SELECT p FROM Product p WHERE p.model LIKE :query")
                .setParameter("query",resultQuery)
                .getResultList();
    }
}
