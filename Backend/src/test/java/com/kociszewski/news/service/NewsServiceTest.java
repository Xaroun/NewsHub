package com.kociszewski.news.service;

import com.kociszewski.news.common.TestParent;
import com.kociszewski.news.entity.News;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by mateusz on 07.06.2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class NewsServiceTest extends TestParent{

    @Mock
    private NewsService newsService;

    @Test
    public void getNews_returnsNewsDetails() {
        given(newsService.getNewsByCountryAndCategory(PL, TECHNOLOGY)).willReturn(Optional.of(getNews()));

        Optional<News> news = newsService.getNewsByCountryAndCategory(PL, TECHNOLOGY);

        assertThat(news.get().getCountry()).isEqualTo(PL);
        assertThat(news.get().getCategory()).isEqualTo(TECHNOLOGY);
        assertThat(news.get().getArticles()).hasSize(1);

        verify(newsService, times(1)).getNewsByCountryAndCategory(PL, TECHNOLOGY);
    }

    @Test
    public void getNews_returnsNewsNotFound() throws Exception{
        given(newsService.getNewsByCountryAndCategory(PL, TECHNOLOGY)).willReturn(Optional.empty());
        Optional<News> news = newsService.getNewsByCountryAndCategory(PL, TECHNOLOGY);

        assertThat(!news.isPresent());

        verify(newsService, times(1)).getNewsByCountryAndCategory(PL, TECHNOLOGY);
    }
}
