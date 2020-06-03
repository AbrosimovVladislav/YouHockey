package ru.yourhockey.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yourhockey.model.filtration.FilterItem;
import ru.yourhockey.web.dto.FilterItemDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FilterItemMapper {

    public FilterItemDto map(FilterItem filterItem) {
        return new FilterItemDto()
                .setMenuItemName(filterItem.getMenuItem())
                .setShowName(filterItem.getName())
                .setFilterKey(filterItem.getKeyPath().getValue())
                .setFilterType(filterItem.getType().name())
                .setRank(filterItem.getRank())
                .setValue(prepareAgeFilterValues(filterItem.getValues(), filterItem.getName()));
    }

    private List<String> prepareAgeFilterValues(List<String> filterValues, String filterName) {
        if (filterName.equalsIgnoreCase("Возраст")) filterValues.remove("UNDEFINED");
        return filterValues;
    }

}
