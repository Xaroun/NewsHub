package com.kociszewski.news.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This test class will cover all corner cases connected with {@link NewsController}
 * Created by mateusz on 06.06.2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(NewsController.class)
public class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getNews_shouldReturnNews() throws Exception {
        mockMvc.perform(get("/news/pl/technology"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("country").value("pl"))
                .andExpect(jsonPath("category").value("technology"))
                .andExpect(jsonPath("articles", hasSize(greaterThan(0))));
    }
}
