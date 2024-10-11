package com.test.practiceProject.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @Project: practiceProject
 * @Author CHUNAM
 * @Date 10/11/2024
 * @Time 9:48 AM
 */
@Service
public class ProductService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${product.url}")
    String productUrl;

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public Object getProduct(String ...args) {
        HttpHeaders headers = new HttpHeaders();
//        header.set(HttpHeaders.ACCEPT, "application/json");
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(productUrl)
                .path("/product")
                .queryParam("limit", 10)
                .queryParam("skip", 10)
                .toUriString();

//        restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, Object.class);

        Gson gson = new Gson();
        Object response;
        response = restTemplate.getForObject(urlTemplate, Object.class);
//        String jsonElement = gson.toJson(response);

//        log.info("Response: {}", jsonElement);
//        log.info("Response: {}", gson.toJson(response));
        for (String arg : args) {
            log.info("Argument: {}", arg);
        }

        return response;
    }
}
