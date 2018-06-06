package com.kociszewski.news;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class NewsHubApplication {

	@Value("${api.url}")
	private String baseUrl;

	public static void main(String[] args) {
		SpringApplication.run(NewsHubApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.rootUri(baseUrl).build();
	}
}
