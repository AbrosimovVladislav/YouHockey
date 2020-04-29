package ru.yourhockey.web.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yourhockey.model.filtration.FilterItem;
import ru.yourhockey.web.dto.FilterItemDto;

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
                .setValue(filterItem.getValues());
    }

}
