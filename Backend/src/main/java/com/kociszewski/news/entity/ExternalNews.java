package com.kociszewski.news.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by mateusz on 07.06.2018.
 */
@Data
public class ExternalNews {
    private String status;
    private long totalResults;
    private List<ExternalArticle> articles;
}
