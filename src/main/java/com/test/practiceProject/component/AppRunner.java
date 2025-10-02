package com.test.practiceProject.component;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.test.practiceProject.entity.Person;
import com.test.practiceProject.service.GithubLookupService;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    public AppRunner(GithubLookupService gitHubLookupService) {
    }

    @Override
    public void run(String... args) {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
//        CompletableFuture<UserGithub> page1 = gitHubLookupService.findUser("PivotalSoftware");
//        CompletableFuture<UserGithub> page2 = gitHubLookupService.findUser("CloudFoundry");
//        CompletableFuture<UserGithub> page3 = gitHubLookupService.findUser("Spring-Projects");
//
//        // Wait until they are all done
//        CompletableFuture.allOf(page1,page2,page3).join();

        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
//        logger.info("--> " + page1.get());
//        logger.info("--> " + page2.get());
//        logger.info("--> " + page3.get());


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

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();

        String json = """
                {
                   "company":"hello world",
                   "data": [
                     {"age": 10, "name": "AAA"},
                     {"age": 20, "name": "BBB"},
                     {"age": 30, "name": "CCC"}
                   ]
                 }
                """;

        // Converts JSON to JsonElement
        JsonElement element = gson.fromJson(json, JsonElement.class);

        Map<String, String> stringMap = new LinkedHashMap<>();
        stringMap.put("key", "value");
        stringMap.put(null, "null-entry");

// Serialization
        String json1 = gson.toJson(stringMap); // ==> {"key":"value","null":"null-entry"}
        System.out.println(json1);

        Type mapType = new TypeToken<Map<String, String>>(){}.getType();
        String json3 = "{\"key\": \"value\"}";

        Map<String, String> stringMap1 = gson.fromJson(json3, mapType);
        System.out.println(stringMap1);

        if (element.isJsonObject()) {

            JsonObject obj = element.getAsJsonObject();

            // Get the `data` array
            JsonArray data = obj.getAsJsonArray("data");

            // creates a List<Person> type
            Type personListType = new TypeToken<List<Person>>() {}.getType();

            // converts the `data` array to List<Person>
            List<Person> list = gson.fromJson(data, personListType);
            System.out.println(list);

        }
    }
}
