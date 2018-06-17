package com.kociszewski.news.common;

import com.kociszewski.news.entity.Article;
import com.kociszewski.news.entity.News;
import com.kociszewski.news.entity.QueryNews;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateusz on 09.06.2018.
 */
public class TestParent {

    public static final String PL = "pl";
    public static final String TECHNOLOGY = "technology";
    public static final String NEW_YORK = "new york";
    public static final String PAGE_SIZE = "1";
    public static final String PAGE_NUMBER = "1";
    public static final String BUSINESS = "business";
    public static final String BIZNES = "Biznes";
    public static final String LINK = "https://material.io/tools/icons/static/icons/baseline-work-24px.svg";

    private News news;
    private QueryNews queryNews;

    @Before
    public void before() {
        List<Article> articles = new ArrayList<>();
        articles.add(Article.builder()
                .author("Author")
                .title("Title")
                .description("Description")
                .date("Date")
                .sourceName("SourceName")
                .articleUrl("ArticleUrl")
                .imageUrl("ImageURL")
                .build());

        news = News.builder()
                .country(PL)
                .category(TECHNOLOGY)
                .articles(articles)
                .build();

        queryNews = QueryNews.builder()
                .query(NEW_YORK)
                .articles(articles)
                .build();
    }

    protected News getNews() {
        return news;
    }

    protected QueryNews getQueryNews() {
        return queryNews;
    }
}
