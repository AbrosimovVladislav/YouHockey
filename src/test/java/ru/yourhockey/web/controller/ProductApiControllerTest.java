package ru.yourhockey.web.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ProductApiControllerTest {
    @Autowired private MockMvc mockMvc;
    private static final String BASE_LOCAL_PATH = "http://localhost:8082";
    private static final String PRODUCTS_PATH = "/products";
    private static final String QUESTION_DELIM = "?";
    private static final String MIN_PRICE_PARAM = "minPrice";
    private static final String EQUALS_DELIM = "=";

    @Test
    public void test() throws Exception {
        String uri = BASE_LOCAL_PATH + PRODUCTS_PATH + QUESTION_DELIM + MIN_PRICE_PARAM + EQUALS_DELIM + "100";

        mockMvc.perform(get(uri))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", (empty())));
    }

}
