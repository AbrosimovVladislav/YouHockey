package ru.yourhockey;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    //deployment test comment
    @GetMapping("/")
    public String index() {
        return "redirect:swagger-ui.html";
    }

}
