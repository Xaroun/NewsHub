package com.kociszewski.news.service;

import com.kociszewski.news.entity.Category;

import java.util.List;

/**
 * Created by mateusz on 17.06.2018.
 */
public interface CategoryService {
    List<Category> getCategories();
}
