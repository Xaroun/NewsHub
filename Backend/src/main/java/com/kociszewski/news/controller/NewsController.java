package com.kociszewski.news.controller;

import com.kociszewski.news.entity.News;
import com.kociszewski.news.service.NewsService;
import com.kociszewski.news.service.NewsServiceImpl;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
    @ApiOperation("Get news by country and category")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "country", value = "Country shortcut (for example pl/en/de)", required = true),
            @ApiImplicitParam(name = "category", value = "Category (for example technology/health)", required = true)
    })
    public ResponseEntity<News> getNewsByCountryAndCategory(@PathVariable String country, @PathVariable String category) {
        Optional<News> news = newsService.getNewsByCountryAndCategory(country, category);
        if(news.isPresent()) {
            return ResponseEntity.ok(news.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
