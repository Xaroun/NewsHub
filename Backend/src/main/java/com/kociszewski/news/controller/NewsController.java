package com.kociszewski.news.controller;

import com.kociszewski.news.entity.News;
import com.kociszewski.news.service.NewsService;
import com.kociszewski.news.service.NewsServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mateusz on 06.06.2018.
 */
@RestController
@RequestMapping(value = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping(value = "/{country}/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<News> getNewsByCountryAndCategory(@PathVariable String country, @PathVariable String category) {
        News news = newsService.getNewsByCountryAndCategory(country, category);
        return ResponseEntity.ok(news);
    }
}
