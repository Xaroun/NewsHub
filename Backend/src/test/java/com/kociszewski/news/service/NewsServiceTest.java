package com.kociszewski.news.service;

import com.kociszewski.news.entity.Article;
import com.kociszewski.news.entity.News;
import com.kociszewski.news.exception.NewsNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by mateusz on 07.06.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class NewsServiceTest {

    private static final String PL = "pl";
    private static final String TECHNOLOGY = "technology";

    @Mock
    private NewsService newsService;

    private List<Article> articles;

    @Before
    public void before() {
        articles = new ArrayList<>();
        articles.add(new Article("Author", "Title",
                "Description", "Date", "SourceName",
                "ArticleUrl", "ImageUrl"));
    }

    @Test
    public void getNews_returnsNewsDetails() {
        given(newsService.getNewsByCountryAndCategory(PL, TECHNOLOGY)).willReturn(new News(PL, TECHNOLOGY, articles));

        News news = newsService.getNewsByCountryAndCategory(PL, TECHNOLOGY);

        assertThat(news.getCountry()).isEqualTo(PL);
        assertThat(news.getCategory()).isEqualTo(TECHNOLOGY);
        assertThat(news.getArticles()).hasSize(1);

        verify(newsService, times(1)).getNewsByCountryAndCategory(PL, TECHNOLOGY);
    }

    @Test
    public void getNews_returnsNewsNotFound() throws Exception{
        given(newsService.getNewsByCountryAndCategory(PL, TECHNOLOGY)).willReturn(null);
        News news = newsService.getNewsByCountryAndCategory(PL, TECHNOLOGY);

        assertThat(news).isEqualTo(null);

        verify(newsService, times(1)).getNewsByCountryAndCategory(PL, TECHNOLOGY);
    }
}
