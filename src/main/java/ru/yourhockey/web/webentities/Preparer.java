package ru.yourhockey.web.webentities;

import ru.yourhockey.model.BasicEntity;

public interface Preparer {
    void prepare(FilterAndPageable filterAndPageable, Class<? extends BasicEntity> entityClass);
}
