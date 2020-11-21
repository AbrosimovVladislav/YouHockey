package ru.yourhockey.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yourhockey.model.product_attributes.Type;
import ru.yourhockey.service.TypeService;
import ru.yourhockey.service.logging.MeasurePerformance;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TypeApiController implements TypeApi {

    private final TypeService typeService;

    @CrossOrigin
    @MeasurePerformance
    @GetMapping("/types")
    public List<Type> types(){
        return typeService.findAll();
    }

}
