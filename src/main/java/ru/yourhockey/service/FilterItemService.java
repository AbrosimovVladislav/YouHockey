package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yourhockey.model.filtration.FilterItem;
import ru.yourhockey.model.filtration.KeyPath;
import ru.yourhockey.repo.FilterItemRepo;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilterItemService {

    private final FilterItemRepo filterItemRepo;

    public FilterItem createFilterItem(FilterItem filterItem) {
        Optional<FilterItem> fiOptional = filterItemRepo.findByNameAndMenuItem(filterItem.getName(), filterItem.getMenuItem());
        if (fiOptional.isPresent()) {
            filterItem.setFilterItemId(fiOptional.get().getFilterItemId());
            return filterItemRepo.save(filterItem);
        }
        return filterItemRepo.save(filterItem);
    }

    public List<FilterItem> getFiltersByMenuItem(String menuItem) {
        List<FilterItem> filterItems = filterItemRepo.findAllByMenuItem(menuItem);
        filterItems.forEach(this::determineValues);
        filterItems.sort(Comparator.comparing(FilterItem::getRank));
        return filterItems;
    }

    private void determineValues(FilterItem filterItem) {
        KeyPath keyPath = filterItem.getKeyPath();
        List<String> values = filterItemRepo.selectFromDistinct(keyPath.getTargetParam(), keyPath.getTargetEntity());
        FilterItem.FilterType type = filterItem.getType();

        switch (type) {
            case CHECKBOX -> filterItem.setValues(values);
            case RANGE -> filterItem.setValues(getMinMax(values));
            default -> throw new UnsupportedOperationException(
                    "Filter type of " + filterItem.getFilterItemId() + " " + filterItem.getName() + " is wrong"
            );
        }
    }

    private List<String> getMinMax(List<String> values) {
        double min = Double.MAX_VALUE;
        double max = 0.;
        for (String value : values) {
            double currentValue = Double.parseDouble(value);
            min = Math.min(currentValue, min);
            max = Math.max(currentValue, max);
        }
        return List.of(String.valueOf(min), String.valueOf(max));
    }
}
