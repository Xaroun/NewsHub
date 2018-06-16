# NewsHub

Simple application for displaying news with two use cases:
* show polish news by category
* search for all news by any phrase

## Stack
* Spring Boot
* Angular
* Docker
* Documentation in Swagger

## Requirements
### To run:
* Docker

### To develop:
* Node.js
* Java
* Gradle

## Running application
### 'prod' mode
1. Type `docker-compose up` and wait until Docker downloads all required images from DockerHub and start containers.
1. Verify if everything is up by navigating to `localhost` in your web browser.

### 'dev' mode
1. Build backend using gradle (`gradle build` or using IDE).
1. Type `docker-compose -f docker-compose-dev.yml up`.
1. Wait until images will be rebuilt and containers started.
1. Verify your changes.

### Powered by [NewsAPI.org](https://newsapi.org)
