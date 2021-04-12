package ru.yourhockey.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yourhockey.model.filtration.FilterItem;
import ru.yourhockey.model.filtration.KeyPath;
import ru.yourhockey.repo.FilterItemRepo;

import java.util.ArrayList;
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
        values.remove(null);
        if (filterItem.getName().equalsIgnoreCase("Брэнд")) {
            values = sortBrandFilterItem(values);
        }
//        if(filterItem.getName().equalsIgnoreCase("Возраст")){
//            values = List.of("SR","INT","JR","YTH");
//        }
        FilterItem.FilterType type = filterItem.getType();

        switch (type) {
            case CHECKBOX:
                filterItem.setValues(values);
                break;
            case RANGE:
                filterItem.setValues(getMinMax(values));
                break;
            default:
                throw new UnsupportedOperationException(
                        "Filter type of " + filterItem.getFilterItemId() + " " + filterItem.getName() + " is wrong"
                );
        }
    }

    //Переделать в нормальный ранкинг для фильтров (каждый filter checkbox - enum)
    private List<String> sortBrandFilterItem(List<String> values) {
        List<String> sortedValues = new ArrayList<>(
                List.of("Brand1",
                        "Brand2",
                        "Brand3")
        );
        values.stream()
                .map(String::toUpperCase)
                .filter(b -> !sortedValues.contains(b))
                .forEach(sortedValues::add);
        return sortedValues;
    }

    private List<String> getMinMax(List<String> values) {
        if(values.isEmpty()){
            return List.of("0","0");
        }
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
