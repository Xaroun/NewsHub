package com.kociszewski.news.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * Builder used to hide logic of adding necessary authorization header
 * Created by mateusz on 07.06.2018.
 */
@Component
class HttpEntityBuilder {

    @Value("${api.key}")
    private String apiKey;

    <T> HttpEntity<T> buildHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        String BEARER = "Bearer %s";
        String AUTHORIZATION = "Authorization";
        headers.set(AUTHORIZATION, String.format(BEARER, apiKey));
        return new HttpEntity<>(headers);
    }
}
