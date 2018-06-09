package com.kociszewski.news.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by mateusz on 06.06.2018.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private String country;
    private String category;
    private List<Article> articles;
}
