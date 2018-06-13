package com.kociszewski.news.service;

import com.kociszewski.news.entity.News;
import com.kociszewski.news.entity.QueryNews;
import com.kociszewski.news.exception.NewsNotFoundException;

import java.util.Optional;

/**
 * Created by mateusz on 09.06.2018.
 */
public interface NewsService {
    Optional<News> getNewsByCountryAndCategory(String country, String category) throws NewsNotFoundException;

    Optional<QueryNews> getNewsByQuery(String query);
}
