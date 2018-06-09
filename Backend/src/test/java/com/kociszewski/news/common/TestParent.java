package com.kociszewski.news.common;

import com.kociszewski.news.entity.Article;
import com.kociszewski.news.entity.News;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateusz on 09.06.2018.
 */
public class TestParent {

    public static final String PL = "pl";
    public static final String TECHNOLOGY = "technology";
    private List<Article> articles;
    private News news;

    @Before
    public void before() {
        articles = new ArrayList<>();
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
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
