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
public class NewsServiceImpl implements NewsService{

    private final NewsRestTemplate newsRestTemplate;

    public NewsServiceImpl(NewsRestTemplate newsRestTemplate) {
        this.newsRestTemplate = newsRestTemplate;
    }

    public News getNewsByCountryAndCategory(String country, String category){
        String uri = String.format("/top-headlines?country=%s&category=%s", country, category);
        ResponseEntity<ExternalNews> result = newsRestTemplate.getRequest(uri, ExternalNews.class);

        List<Article> articles = parseExternalArticles(result.getBody().getArticles());
        return News.builder()
                .country(country)
                .category(category)
                .articles(articles)
                .build();
    }

    private List<Article> parseExternalArticles(List<ExternalArticle> externalArticles) {
        return externalArticles.stream().map(extArticle ->
                Article.builder()
                        .author(extArticle.getAuthor())
                        .title(extArticle.getTitle())
                        .description(extArticle.getDescription())
                        .date(extArticle.getPublishedAt())
                        .sourceName(extArticle.getSource().getName())
                        .articleUrl(extArticle.getUrl())
                        .imageUrl(extArticle.getUrlToImage())
                        .build())
                .collect(Collectors.toList());
    }
}
