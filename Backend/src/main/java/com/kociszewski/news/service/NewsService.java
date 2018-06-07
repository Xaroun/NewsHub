package com.kociszewski.news.service;

import com.kociszewski.news.component.NewsRestTemplate;
import com.kociszewski.news.entity.Article;
import com.kociszewski.news.entity.ExternalArticle;
import com.kociszewski.news.entity.ExternalNews;
import com.kociszewski.news.entity.News;
import com.kociszewski.news.exception.NewsNotFoundException;
import org.springframework.http.HttpStatus;
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

    public News getNewsByCountryAndCategory(String country, String category) throws NewsNotFoundException {
        String uri = String.format("/top-headlines?country=%s&category=%s", country, category);
        ResponseEntity<ExternalNews> result = newsRestTemplate.getRequest(uri, ExternalNews.class);

        if(HttpStatus.NOT_FOUND.equals(result.getStatusCode())) {
            throw new NewsNotFoundException();
        }

        List<Article> articles = parseExternalArticles(result.getBody().getArticles());
        return new News(country, category, articles);
    }

    private List<Article> parseExternalArticles(List<ExternalArticle> externalArticles) {
        return externalArticles.stream().map(extArticle ->
                new Article(extArticle.getAuthor(),
                        extArticle.getTitle(),
                        extArticle.getDescription(),
                        extArticle.getPublishedAt(),
                        extArticle.getSource().getName(),
                        extArticle.getUrl(),
                        extArticle.getUrlToImage()))
                .collect(Collectors.toList());
    }
}
