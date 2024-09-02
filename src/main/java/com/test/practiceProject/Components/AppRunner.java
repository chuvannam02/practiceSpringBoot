package com.test.practiceProject.Components;

import com.test.practiceProject.Response.UserGithub;
import com.test.practiceProject.Service.GithubLookupService;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.concurrent.CompletableFuture;

@Component
public class AppRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final GithubLookupService gitHubLookupService;

    public AppRunner(GithubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        CompletableFuture<UserGithub> page1 = gitHubLookupService.findUser("PivotalSoftware");
        CompletableFuture<UserGithub> page2 = gitHubLookupService.findUser("CloudFoundry");
        CompletableFuture<UserGithub> page3 = gitHubLookupService.findUser("Spring-Projects");

        // Wait until they are all done
        CompletableFuture.allOf(page1,page2,page3).join();

        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("--> " + page1.get());
        logger.info("--> " + page2.get());
        logger.info("--> " + page3.get());


        // storing multiple values associate with a single key
        // provide multiple implementations of MultiValuedMap interface
        MultiValuedMap<String, String> multiValuedMap = new ArrayListValuedHashMap<>();

        // Adding values
        multiValuedMap.put("fruit", "apple");
        multiValuedMap.put("fruit", "banana");
        multiValuedMap.put("fruit", "orange");

        // Retrieving values
        System.out.println("Fruit values: " + multiValuedMap.get("fruit"));

        // Iterating through the map
        multiValuedMap.entries().forEach(entry ->
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue()));
    }
}
