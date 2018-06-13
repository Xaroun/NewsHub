package com.kociszewski.news.controller;

import com.kociszewski.news.common.TestParent;
import com.kociszewski.news.exception.BadRequestException;
import com.kociszewski.news.exception.NewsNotFoundException;
import com.kociszewski.news.exception.UnauthorizedException;
import com.kociszewski.news.service.NewsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This test class will cover all corner cases connected with {@link NewsController}
 * Created by mateusz on 06.06.2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(NewsController.class)
public class NewsControllerTest extends TestParent {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsService newsService;

    @Test
    public void getTechnologyNewsFromPoland_shouldReturnProperNews() throws Exception {
        given(newsService.getNewsByCountryAndCategory(PL, TECHNOLOGY)).
                willReturn(Optional.of(getNews()));

        mockMvc.perform(get("/news/pl/technology"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("country").value(PL))
                .andExpect(jsonPath("category").value(TECHNOLOGY))
                .andExpect(jsonPath("articles", hasSize(greaterThan(0))));

        verify(newsService, times(1)).getNewsByCountryAndCategory(PL, TECHNOLOGY);
    }

    @Test
    public void getTechnologyNewsFromPoland_notFound() throws Exception {
        given(newsService.getNewsByCountryAndCategory(anyString(), anyString())).willThrow(new NewsNotFoundException());

        mockMvc.perform(get("/news/pl/technology"))
                .andExpect(status().isNotFound());

        verify(newsService, times(1)).getNewsByCountryAndCategory(anyString(), anyString());
    }

    @Test
    public void getTechnologyNewsFromPoland_withInvalidApiKey() throws Exception {
        given(newsService.getNewsByCountryAndCategory(anyString(), anyString())).willThrow(new UnauthorizedException());

        mockMvc.perform(get("/news/pl/technology"))
                .andExpect(status().isUnauthorized());

        verify(newsService, times(1)).getNewsByCountryAndCategory(anyString(), anyString());
    }

    @Test
    public void getQueryNews_shouldReturnProperNews() throws Exception {
        given(newsService.getNewsByQuery(anyString())).willReturn(Optional.of(getQueryNews()));

        mockMvc.perform(get("/news").param("search", NEW_YORK))
                .andExpect(status().isOk())
                .andExpect(jsonPath("query").value(NEW_YORK))
                .andExpect(jsonPath("articles", hasSize(greaterThan(0))));

        verify(newsService, times(1)).getNewsByQuery(NEW_YORK);
    }

    @Test
    public void getQueryNews_withInvalidApiKey() throws Exception {
        given(newsService.getNewsByQuery(anyString())).willThrow(new UnauthorizedException());

        mockMvc.perform(get("/news").param("search", NEW_YORK))
                .andExpect(status().isUnauthorized());

        verify(newsService, times(1)).getNewsByQuery(anyString());
    }

    @Test
    public void getQueryNews_withEmptyQuery() throws Exception {
        given(newsService.getNewsByQuery(anyString())).willThrow(new BadRequestException("Obligatory search param is empty"));

        mockMvc.perform(get("/news").param("search", ""))
                .andExpect(status().isBadRequest());

        verify(newsService, times(1)).getNewsByQuery(anyString());
    }

}
