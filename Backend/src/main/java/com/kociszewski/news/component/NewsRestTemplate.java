package com.kociszewski.news.component;

import com.kociszewski.news.exception.NewsNotFoundException;
import com.kociszewski.news.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 * Wrapper used to hide logic connected with {@link HttpEntity}, additionaly set baseUrl as a default one
 *
 * Created by mateusz on 07.06.2018.
 */
@Component
public class NewsRestTemplate {

    private final RestTemplate restTemplate;
    private final HttpEntityBuilder httpEntityBuilder;

    @Autowired
    public NewsRestTemplate(RestTemplate restTemplate, HttpEntityBuilder httpEntityBuilder, @Value("${api.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));
        this.httpEntityBuilder = httpEntityBuilder;
    }

    public <T> ResponseEntity<T> getRequest(String uri,  Class<T> responseType) {
        HttpEntity<T> httpEntity = httpEntityBuilder.buildHttpEntity();
        try {
            return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, responseType);
        } catch (HttpClientErrorException ex) {
            switch(ex.getStatusCode()) {
                case UNAUTHORIZED:
                    throw new UnauthorizedException("Invalid API key");
                case NOT_FOUND:
                    throw new NewsNotFoundException("News not found");
            }
        }
        return ResponseEntity.notFound().build();
    }
}
