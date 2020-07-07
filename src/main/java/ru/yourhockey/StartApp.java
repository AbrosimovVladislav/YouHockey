package ru.yourhockey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.annotation.RequestScope;

import javax.persistence.criteria.Path;
import java.util.HashMap;
import java.util.Map;

@EnableScheduling
@SpringBootApplication
public class StartApp {

    public static void main(String[] args) {
        SpringApplication.run(StartApp.class);
    }

    @Bean
    @RequestScope
    public Map<String, Path> pathCache() {
        return new HashMap<>();
    }
}
