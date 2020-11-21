package ru.yourhockey.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.yourhockey.model.filtration.FilterItem;
import ru.yourhockey.service.FilterItemService;
import ru.yourhockey.service.logging.MeasurePerformance;
import ru.yourhockey.web.dto.FilterItemDto;
import ru.yourhockey.web.mapper.FilterItemMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FilterApiController implements FilterApi {

    private final FilterItemService filterItemService;
    private final FilterItemMapper filterItemMapper;

    /*ToDo Выбрать колонку в которой лежат не строки и проверить работоспособность всей цепочки */
    /*ToDo Подумать над тем, мб пустить фильтер не по меню айтему а по всему тайпу */
    @CrossOrigin
    @MeasurePerformance
    @GetMapping(value = "/product/filters/{menuItem}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FilterItemDto> getFilterItemsByMenuItem(@PathVariable String menuItem) {
        List<FilterItem> filterItems = filterItemService.getFiltersByMenuItem(menuItem);
        return filterItems.stream().map(filterItemMapper::map).collect(Collectors.toList());
    }

}
