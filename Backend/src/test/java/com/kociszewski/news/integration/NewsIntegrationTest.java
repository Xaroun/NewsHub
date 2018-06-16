package com.kociszewski.news.integration;

import com.kociszewski.news.entity.Article;
import com.kociszewski.news.entity.News;
import com.kociszewski.news.entity.QueryNews;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.kociszewski.news.common.TestParent.NEW_YORK;
import static com.kociszewski.news.common.TestParent.PL;
import static com.kociszewski.news.common.TestParent.TECHNOLOGY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

/**
 * This test class will cover integration tests for news (happy path)
 * Created by mateusz on 06.06.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NewsIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getTechnologyNewsFromPoland_returnsProperNewsObject() {
        ResponseEntity<News> response = testRestTemplate.getForEntity("/news/pl/technology", News.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getCountry()).isEqualTo(PL);
        assertThat(response.getBody().getCategory()).isEqualTo(TECHNOLOGY);
        assertThat(response.getBody().getArticles().size()).isNotEqualTo(0);

        articleIsOk(response.getBody().getArticles().get(0));
    }

    @Test
    public void getQueryNews_returnsProperNewsObject() {
        ResponseEntity<QueryNews> response = testRestTemplate.getForEntity(String.format("/news?search=%s", NEW_YORK), QueryNews.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getQuery()).isEqualTo(NEW_YORK);
        assertThat(response.getBody().getArticles().size()).isGreaterThan(1);

        articleIsOk(response.getBody().getArticles().get(0));
    }

    @Test
    public void getQueryNewsWithPaging_returnsProperNewsObject() {
        ResponseEntity<QueryNews> response = testRestTemplate.getForEntity(String.format("/news?search=%s&pageSize=%d&pageNumber=%d", NEW_YORK, 2, 1), QueryNews.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getQuery()).isEqualTo(NEW_YORK);
        assertThat(response.getBody().getArticles().size()).isEqualTo(2);

        articleIsOk(response.getBody().getArticles().get(0));
    }

    private void articleIsOk(Article article) {
        assertThat(article.getArticleUrl()).isNotEmpty();
        assertThat(article.getDate()).isNotEmpty();
        assertThat(article.getSourceName()).isNotEmpty();
        assertThat(article.getTitle()).isNotEmpty();
    }
}
