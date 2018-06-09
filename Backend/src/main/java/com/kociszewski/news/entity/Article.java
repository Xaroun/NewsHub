package com.kociszewski.news.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mateusz on 06.06.2018.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private String author;
    private String title;
    private String description;
    private String date;
    private String sourceName;
    private String articleUrl;
    private String imageUrl;
}
