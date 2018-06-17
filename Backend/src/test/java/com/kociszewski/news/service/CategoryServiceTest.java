package com.kociszewski.news.service;

import com.kociszewski.news.entity.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.kociszewski.news.common.TestParent.BIZNES;
import static com.kociszewski.news.common.TestParent.BUSINESS;
import static com.kociszewski.news.common.TestParent.LINK;
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
                .id(BUSINESS)
                .name(BIZNES)
                .iconUrl(LINK)
                .build();

        categories.add(businessCategory);
    }

    @Test
    public void getCategory_returnCategories() {
        given(categoryService.getCategories()).willReturn(categories);

        List<Category> categories = categoryService.getCategories();

        assertThat(categories.size()).isEqualTo(1);
        assertThat(categories.get(0).getId()).isEqualTo(BUSINESS);
        assertThat(categories.get(0).getName()).isEqualTo(BIZNES);
        assertThat(categories.get(0).getIconUrl()).isEqualTo(LINK);
    }
}
