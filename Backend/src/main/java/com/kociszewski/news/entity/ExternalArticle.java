package com.kociszewski.news.entity;

import lombok.Data;

/**
 * Created by mateusz on 07.06.2018.
 */
@Data
public class ExternalArticle {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
}
