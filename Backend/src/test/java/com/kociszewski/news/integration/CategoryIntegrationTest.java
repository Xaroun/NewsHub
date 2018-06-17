package com.kociszewski.news.integration;

import com.kociszewski.news.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by mateusz on 17.06.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getCategory_returnsCategories() {
        ResponseEntity<Category[]> response = testRestTemplate.getForEntity("/categories", Category[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Category> categories = Arrays.asList(response.getBody());
        assertThat(categories.size()).isEqualTo(7);
        assertThat(categories.get(0).getId()).isEqualTo("business");
        assertThat(categories.get(0).getName()).isEqualTo("Biznes");
        assertThat(categories.get(0).getIconUrl()).isEqualTo("https://material.io/tools/icons/static/icons/baseline-work-24px.svg");

    }
}
