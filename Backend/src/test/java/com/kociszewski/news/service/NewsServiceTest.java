package com.kociszewski.news.service;

import com.kociszewski.news.common.TestParent;
import com.kociszewski.news.entity.News;
import com.kociszewski.news.entity.QueryNews;
import com.kociszewski.news.exception.BadRequestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
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
        given(newsService.getNewsByCountryAndCategory(anyString(), anyString())).willReturn(Optional.of(getNews()));

        Optional<News> news = newsService.getNewsByCountryAndCategory(PL, TECHNOLOGY);

        assertThat(news.get().getCountry()).isEqualTo(PL);
        assertThat(news.get().getCategory()).isEqualTo(TECHNOLOGY);
        assertThat(news.get().getArticles()).hasSize(1);

        verify(newsService, times(1)).getNewsByCountryAndCategory(PL, TECHNOLOGY);
    }

    @Test
    public void getNews_returnsEmptyOptional() throws Exception{
        given(newsService.getNewsByCountryAndCategory(anyString(), anyString())).willReturn(Optional.empty());
        Optional<News> news = newsService.getNewsByCountryAndCategory(PL, TECHNOLOGY);

        assertThat(!news.isPresent());

        verify(newsService, times(1)).getNewsByCountryAndCategory(PL, TECHNOLOGY);
    }

    @Test
    public void getQueryNews_returnsNewsDetails() throws Exception {
        given(newsService.getNewsByQuery(anyString(), anyInt(), anyInt())).willReturn(Optional.of(getQueryNews()));

        Optional<QueryNews> queryNews = newsService.getNewsByQuery(NEW_YORK, Integer.parseInt(PAGE_SIZE), Integer.parseInt(PAGE_NUMBER));

        assertThat(queryNews.get().getQuery()).isEqualTo(NEW_YORK);
        assertThat(queryNews.get().getArticles()).hasSize(1);

        verify(newsService, times(1)).getNewsByQuery(NEW_YORK, Integer.parseInt(PAGE_SIZE), Integer.parseInt(PAGE_NUMBER));
    }

    @Test(expected = BadRequestException.class)
    public void getQueryNews_throwsBadRequestException() throws Exception {
        given(newsService.getNewsByQuery(anyString(), anyInt(), anyInt())).willThrow(new BadRequestException("Obligatory search param is empty"));

        Optional<QueryNews> queryNews = newsService.getNewsByQuery("", Integer.parseInt(PAGE_SIZE), Integer.parseInt(PAGE_NUMBER));
        verify(newsService, times(1)).getNewsByQuery(NEW_YORK, Integer.parseInt(PAGE_SIZE), Integer.parseInt(PAGE_NUMBER));
    }
}
