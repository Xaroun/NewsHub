package com.kociszewski.news.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by mateusz on 06.06.2018.
 */
public class News {
    private String country;
    private String category;
    private List<Article> articles;

    public News() {
    }

    public News(String country, String category, List<Article> articles) {
        this.country = country;
        this.category = category;
        this.articles = articles;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
