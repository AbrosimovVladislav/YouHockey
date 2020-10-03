package ru.yourhockey.web.preparer;

import ru.yourhockey.model.BasicEntity;

public interface Preparer {
    void prepare(FilterAndPageable filterAndPageable, Class<? extends BasicEntity> entityClass);
}
