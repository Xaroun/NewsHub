package com.kociszewski.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mateusz on 06.06.2018.
 */
@RestController
public class NewsController {

    @Autowired
    private RestTemplate restTemplate;
}
