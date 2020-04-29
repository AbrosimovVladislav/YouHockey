package ru.yourhockey.repo.selectfrom;

import java.util.List;

public interface SelectFromRepo {

    List<String> selectFrom(String column, String table);
    List<String> selectFromDistinct(String column, String table);

}
