package com.kociszewski.news.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by mateusz on 13.06.2018.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryNews {
    private String query;
    private List<Article> articles;
}
