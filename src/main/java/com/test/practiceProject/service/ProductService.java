package com.test.practiceProject.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.practiceProject.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Project: practiceProject
 * @Author CHUNAM
 * @Date 10/11/2024
 * @Time 9:48 AM
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProductService {
    final RestTemplate restTemplate;
    final ProductRepository productRepository;

    @Value("${product.url}")
    String productUrl;

    static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public Object getProduct(String ...args) {
        HttpHeaders headers = new HttpHeaders();
//        header.set(HttpHeaders.ACCEPT, "application/json");
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(productUrl)
                .path("/product")
//                .queryParam("limit", 10)
                .queryParam("limit", "{limit}")
//                .queryParam("skip", 10)
                .queryParam("skip", "{skip}")
                .encode()
                .toUriString();

//        restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, Object.class);
        Map<String, Integer> params = new HashMap<>();
        params.put("limit", 1);
        params.put("skip", 10);

//        Object response = restTemplate.getForObject(urlTemplate, ProductDTO.class, params);
//        Gson gson = new Gson();
        Object response = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, Object.class, params).getBody();

//        Object response = restTemplate.getForObject(urlTemplate, Object.class);
//        String jsonElement = gson.toJson(response);

//        log.info("Response: {}", jsonElement);
//        log.info("Response: {}", gson.toJson(response));
        for (String arg : args) {
            log.info("Argument: {}", arg);
        }

        return response;
    }

    public void test() {
        Gson gson = new Gson();
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("name", "Chunam");
        inputMap.put("age", "30");
        // Serialization
        String json = gson.toJson(inputMap);
//        System.out.println("Json: " + json);
        // {"site":"https://gpcoder.com","name":"GP Coder"}

        // Deserialization
        Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, String> outputMap = gson.fromJson(json, mapType);
//        System.out.println("Output Map: " + outputMap);

        MultiValuedMap<String, Object> nestedObject = new ArrayListValuedHashMap<>();
        nestedObject.put("name", "Chunam");
        nestedObject.put("name", "Tran Trung Hieu");
        nestedObject.put("age", 30);
        nestedObject.put("age", 31);

//        for (String key : nestedObject.keySet()) {
//            System.out.println(key + ": " + nestedObject.get(key));
//        }

        // Serialization
        String nestedJson = gson.toJson(nestedObject);
//        log.info("Nested Json: {}", nestedJson);

        Type map1 = new TypeToken<Map<String, Object>>(){}.getType();
        Map<String, Object> outputNestedMap = gson.fromJson(nestedJson, map1);
//        log.info("Output Nested Map: {}", outputNestedMap);
//        JsonArray jsonArray = gson.fromJson(nestedJson, JsonArray.class);
//        System.out.println("Json Array: " + jsonArray);

        JSONObject jo = new JSONObject();
        jo.put("name", "jon doe");
        jo.put("age", "22");
        jo.put("city", "chicago");

        Map<String, String> map = new HashMap<>();
        map.put("name", "jon doe");
        map.put("age", "22");
        map.put("city", "chicago");
        JSONObject jo1 = new JSONObject(map);

        JSONObject jo2 = new JSONObject(
                "{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}"
        );

        JSONArray ja = new JSONArray();
        ja.put(Boolean.TRUE);
        ja.put("lorem ipsum");

        JSONObject jo3 = new JSONObject();
        jo3.put("name", "jon doe");
        jo3.put("age", "22");
        jo3.put("city", "chicago");

        ja.put(jo);

        Iterator<Object> iterator = ja.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
