package com.kociszewski.news.controller;

import com.kociszewski.news.entity.News;
import com.kociszewski.news.service.NewsService;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by mateusz on 06.06.2018.
 */
@RestController
@RequestMapping(value = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
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
        return news.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
