package com.kociszewski.news.service;

import com.kociszewski.news.component.NewsRestTemplate;
import com.kociszewski.news.entity.*;
import com.kociszewski.news.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<News> getNewsByCountryAndCategory(String country, String category){
        String uri = String.format("/top-headlines?country=%s&category=%s", country, category);
        ResponseEntity<ExternalNews> result;

        try {
           result = newsRestTemplate.getRequest(uri, ExternalNews.class);
        } catch (HttpClientErrorException ex) {
            return Optional.empty();
        }

        List<Article> articles = parseExternalArticles(result.getBody().getArticles());
        return Optional.of(News.builder()
                .country(country)
                .category(category)
                .articles(articles)
                .build());
    }

    @Override
    public Optional<QueryNews> getNewsByQuery(String query, int pageSize, int pageNumber) {
        if(query.isEmpty()) {
            throw new BadRequestException("Obligatory search param is empty");
        }

        String uri = String.format("/everything?q=%s&pageSize=%d&page=%d", query, pageSize, pageNumber);
        ResponseEntity<ExternalNews> result;

        try {
            result = newsRestTemplate.getRequest(uri, ExternalNews.class);
        } catch (HttpClientErrorException ex) {
            return Optional.empty();
        }

        List<Article> articles = parseExternalArticles(result.getBody().getArticles());
        return Optional.of(QueryNews.builder()
                .query(query)
                .articles(articles)
                .build());
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
