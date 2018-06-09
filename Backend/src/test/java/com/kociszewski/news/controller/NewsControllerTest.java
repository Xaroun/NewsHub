package com.kociszewski.news.controller;

import com.kociszewski.news.common.TestParent;
import com.kociszewski.news.component.NewsRestTemplate;
import com.kociszewski.news.entity.Article;
import com.kociszewski.news.entity.News;
import com.kociszewski.news.exception.NewsNotFoundException;
import com.kociszewski.news.service.NewsService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
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
                willReturn(getNews());

        mockMvc.perform(get("/news/pl/technology"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("country").value(PL))
                .andExpect(jsonPath("category").value(TECHNOLOGY))
                .andExpect(jsonPath("articles", hasSize(greaterThan(0))));

        verify(newsService, times(1)).getNewsByCountryAndCategory(PL, TECHNOLOGY);
    }

    @Test
    public void getTechnologyNewsFromPoland_notFound() throws Exception {
        given(newsService.getNewsByCountryAndCategory(Mockito.anyString(), Mockito.any())).willThrow(new NewsNotFoundException());

        mockMvc.perform(get("/news/pl/technology"))
                .andExpect(status().isNotFound());

        verify(newsService, times(1)).getNewsByCountryAndCategory(Mockito.anyString(), Mockito.any());
    }

}
