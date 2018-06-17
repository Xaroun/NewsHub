package com.kociszewski.news.service;

import com.kociszewski.news.entity.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mateusz on 17.06.2018.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private List<Category> categories;
    private String[] ids = {
            "business", "entertainment"
            , "general", "health", "science"
            , "sport", "technology"};

    private String[] names = {
            "Biznes", "Rozrywka"
            , "Ogólne", "Zdrowie", "Nauka"
            , "Sport", "Technologia"};

    private String[] iconUrls ={
            "https://material.io/tools/icons/static/icons/baseline-work-24px.svg"
            , "https://material.io/tools/icons/static/icons/baseline-insert_emoticon-24px.svg"
            , "https://material.io/tools/icons/static/icons/baseline-public-24px.svg"
            , "https://material.io/tools/icons/static/icons/baseline-favorite-24px.svg"
            , "https://material.io/tools/icons/static/icons/baseline-local_library-24px.svg"
            , "https://material.io/tools/icons/static/icons/baseline-fitness_center-24px.svg"
            , "https://material.io/tools/icons/static/icons/baseline-devices_other-24px.svg"};

    @Override
    public List<Category> getCategories() {
        categories = new ArrayList<>();

        for(int i = 0; i < ids.length; i++) {
            categories.add(Category.builder().id(ids[i]).name(names[i]).iconUrl(iconUrls[i]).build());
        }
        return categories;
    }
}
