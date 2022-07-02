package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String resourceUrl
                = "http://94.198.50.185:7081/api/users";
        User james = new User(3L, "James", "Brown", (byte) 0);
        HttpEntity<String> entity;
        ResponseEntity<String> responseEntity;
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json;

//GET
        responseEntity = restTemplate.getForEntity(resourceUrl, String.class);
        System.out.println(responseEntity.getBody());


        headers.set(HttpHeaders.COOKIE, String.join(";", Objects.requireNonNull(responseEntity.getHeaders().get("Set-Cookie"))));
        headers.set(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");

//POST
        json = ow.writeValueAsString(james);
        entity = new HttpEntity<>(json, headers);
        responseEntity = restTemplate.postForEntity(resourceUrl, entity, String.class);
        System.out.println(responseEntity.getBody());


        james.name = "Thomas";
        james.lastName = "Shelby";

//PUT
        json = ow.writeValueAsString(james);
        entity = new HttpEntity<>(json, headers);
        responseEntity = restTemplate.exchange(
                resourceUrl,
                HttpMethod.PUT,
                entity,
                String.class);
        System.out.println(responseEntity.getBody());

//DELETE
        json = ow.writeValueAsString(james);
        entity = new HttpEntity<>(json, headers);
        responseEntity = restTemplate.exchange(
                resourceUrl + "/3",
                HttpMethod.DELETE,
                entity,
                String.class);
        System.out.println(responseEntity.getBody());
    }
}
