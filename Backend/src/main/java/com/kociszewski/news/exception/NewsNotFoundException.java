package com.kociszewski.news.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mateusz on 07.06.2018.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NewsNotFoundException extends RuntimeException{

    public NewsNotFoundException() {
        super("News not found");
    }
}
