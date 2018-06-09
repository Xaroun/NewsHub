package com.kociszewski.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mateusz on 09.06.2018.
 */
@Controller
@RequestMapping("/")
public class RootController {
    @GetMapping
    public String swaggerUi() {
        return "redirect:/swagger-ui.html";
    }
}
