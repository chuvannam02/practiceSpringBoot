package com.test.practiceProject.Service;

import com.test.practiceProject.Response.UserGithub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class GithubLookupService {
    private static final Logger logger = LoggerFactory.getLogger(GithubLookupService.class);

    private final RestTemplate restTemplate;
    public GithubLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<UserGithub> findUser(String user) throws InterruptedException {
        logger.info("Looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        UserGithub results = restTemplate.getForObject(url, UserGithub.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }

}
