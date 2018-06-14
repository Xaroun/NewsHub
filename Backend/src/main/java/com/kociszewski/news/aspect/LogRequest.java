package com.kociszewski.news.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

/**
 * Created by mateusz on 07.06.2018.
 */
@Component
@Aspect
public class LogRequest {

    private static final Logger logger = LogManager.getLogger();

    @Pointcut("execution(public org.springframework.http.ResponseEntity com.kociszewski.news.controller.*.*(..))")
    public void controllersWithResponseEntity() {
        // pointcut declaration
    }

    @Around("controllersWithResponseEntity()")
    public Object aroundRequest(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

        Instant start = Instant.now();
        ResponseEntity<?> responseEntity = (ResponseEntity<?>) proceedingJoinPoint.proceed();
        Instant end = Instant.now();
        Double invocationTimeInSeconds = (double) Duration.between(start, end).toMillis() / 1000;

        String log = String.format("[%s] request on [%s] with params [%s] from [%s] took [%.2f]s with code [%s]",
                request.getMethod(),
                request.getRequestURI(),
                Optional.ofNullable(request.getQueryString()).orElse("empty"),
                request.getRemoteAddr(),
                invocationTimeInSeconds,
                responseEntity.getStatusCode());

        logger.info(log);

        return responseEntity;
    }

}
