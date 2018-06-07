package com.kociszewski.news.service;

import com.kociszewski.news.component.NewsRestTemplate;
import com.kociszewski.news.entity.Article;
import com.kociszewski.news.entity.ExternalArticle;
import com.kociszewski.news.entity.ExternalNews;
import com.kociszewski.news.entity.News;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mateusz on 07.06.2018.
 */
@Service
public class NewsService {

    private final NewsRestTemplate newsRestTemplate;

    public NewsService(NewsRestTemplate newsRestTemplate) {
        this.newsRestTemplate = newsRestTemplate;
    }

    public News getNewsByCountryAndCategory(String country, String category) {
        String uri = String.format("/top-headlines?country=%s&category=%s", country, category);
        ResponseEntity<ExternalNews> result = newsRestTemplate.getRequest(uri, ExternalNews.class);
        List<ExternalArticle> externalArticles = result.getBody().getArticles();

        List<Article> articles = externalArticles.stream().map(extArticle ->
                new Article(extArticle.getAuthor(),
                        extArticle.getTitle(),
                        extArticle.getDescription(),
                        extArticle.getPublishedAt(),
                        extArticle.getSource().getName(),
                        extArticle.getUrl(),
                        extArticle.getUrlToImage()))
                .collect(Collectors.toList());

       return new News(country, category, articles);
    }
}
