package ru.yourhockey.web.validation;

import org.springframework.stereotype.Component;
import ru.yourhockey.model.product_attributes.Age;
import ru.yourhockey.web.webentities.FilterAndPageable;

import java.util.Map;


@Component
public class RequestParamsValidator {
    public FilterAndPageable validateAgeParam(FilterAndPageable fap) {
        Map<String, String> filter = fap.getFilter();
        if (filter.containsKey("age")) {
            filter.put("age", filter.get("age") + "," + Age.UNDEFINED.name());
            fap.setFilter(filter);
        }
        return fap;
    }
}
