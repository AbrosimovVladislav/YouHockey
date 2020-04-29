package ru.yourhockey.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yourhockey.model.filtration.FilterItem;
import ru.yourhockey.repo.selectfrom.SelectFromRepo;

import java.util.List;


public interface FilterItemRepo extends JpaRepository<FilterItem, Long>, SelectFromRepo {
    List<FilterItem> findAllByMenuItem(String menuItem);
}
