package com.kociszewski.news.entity;

import java.util.List;

/**
 * Created by mateusz on 07.06.2018.
 */
public class ExternalNews {
    private String status;
    private long totalResults;
    private List<ExternalArticle> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public List<ExternalArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<ExternalArticle> articles) {
        this.articles = articles;
    }
}
