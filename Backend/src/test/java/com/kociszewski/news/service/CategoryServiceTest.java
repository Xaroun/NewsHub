package com.kociszewski.news.service;

import com.kociszewski.news.entity.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Created by mateusz on 17.06.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
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
    public void getCategory_returnCategories() {
        given(categoryService.getCategories()).willReturn(categories);

        List<Category> categories = categoryService.getCategories();

        assertThat(categories.size()).isEqualTo(7);
        assertThat(categories.get(0).getId()).isEqualTo("business");
        assertThat(categories.get(0).getName()).isEqualTo("Biznes");
        assertThat(categories.get(0).getIconUrl()).isEqualTo("https://material.io/tools/icons/static/icons/baseline-work-24px.svg");
    }
}
