package com.kociszewski.news.controller;

import com.kociszewski.news.entity.Category;
import com.kociszewski.news.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by mateusz on 17.06.2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private List<Category> categories;

    @Before
    public void setUp() {
        categories = new ArrayList<>();

        Category businessCategory = Category.builder()
                .id("business")
                .name("Biznes")
                .iconUrl("https://material.io/tools/icons/static/icons/baseline-work-24px.svg")
                .build();

        categories.add(businessCategory);
    }

    @Test
    public void getCategories_shouldReturnCategories() throws Exception{
        given(categoryService.getCategories())
                .willReturn(categories);

        mockMvc.perform(get("/category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value("business"))
                .andExpect(jsonPath("[0].name").value("Biznes"))
                .andExpect(jsonPath("[0].iconUrl").value("https://material.io/tools/icons/static/icons/baseline-work-24px.svg"));

        verify(categoryService, times(1)).getCategories();
    }

    @Test
    public void getCategories_shouldReturnNotFound() throws Exception{
        given(categoryService.getCategories())
                .willReturn(Collections.emptyList());

        mockMvc.perform(get("/category"))
                .andExpect(status().isNotFound());

        verify(categoryService, times(1)).getCategories();
    }
}
