package com.kociszewski.news.integration;

import com.kociszewski.news.entity.Article;
import com.kociszewski.news.entity.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * This test class will cover integration tests for news (happy path)
 * Created by mateusz on 06.06.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NewsIntegrationTest {

    private final TestRestTemplate testRestTemplate;

    public NewsIntegrationTest(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @Test
    public void getTechnologyNewsFromPoland_returnsProperNewsObject() {
        ResponseEntity<News> response = testRestTemplate.getForEntity("/news/pl/technology", News.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getCountry()).isEqualTo("pl");
        assertThat(response.getBody().getCategory()).isEqualTo("technology");
        assertThat(response.getBody().getArticles().size()).isNotEqualTo(0);
        Article article = response.getBody().getArticles().get(0);
        assertThat(article.getArticleUrl()).isNotEmpty();
        assertThat(article.getAuthor()).isNotEmpty();
        assertThat(article.getDate()).isNotEmpty();
        assertThat(article.getDescription()).isNotEmpty();
        assertThat(article.getImageUrl()).isNotEmpty();
        assertThat(article.getSourceName()).isNotEmpty();
        assertThat(article.getTitle()).isNotEmpty();
    }
}
