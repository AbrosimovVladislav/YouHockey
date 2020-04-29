package ru.yourhockey.repo.selectfrom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SelectFromRepoImpl implements SelectFromRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<String> selectFrom(String column, String table) {
        return (List<String>) entityManager.createNativeQuery("SELECT " + column + " FROM " + table).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> selectFromDistinct(String column, String table) {
        return (List<String>) entityManager.createNativeQuery("SELECT DISTINCT " + column + " FROM " + table).getResultList();
    }
}
