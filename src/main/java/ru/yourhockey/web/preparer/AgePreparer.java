package ru.yourhockey.web.preparer;

import org.springframework.stereotype.Component;
import ru.yourhockey.model.BasicEntity;
import ru.yourhockey.model.product_attributes.Age;

@Component
public class AgePreparer implements Preparer {
    @Override
    public void prepare(FilterAndPageable filterAndPageable, Class<? extends BasicEntity> entityClass) {
        if (filterAndPageable.getFilter().containsKey("age")) {
            filterAndPageable.getFilter().put("age", filterAndPageable.getFilter().get("age") + "," + Age.UNDEFINED.name());
        }
    }
}
