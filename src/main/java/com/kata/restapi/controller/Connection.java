package com.kata.restapi.controller;

import com.kata.restapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
public class Connection {
    private final RestTemplate restTemplate;
    private String cookie;

    final String url = "http://94.198.50.185:7081/api/users";

    @Autowired
    public Connection(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getAllUsers() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        cookie = Objects.requireNonNull(forEntity.getHeaders().get("Set-Cookie")).getFirst();
        return restTemplate.exchange(url,
                HttpMethod.GET, null, String.class).getBody();
    }

    @PostMapping
    public String saveUser(User user) {
        return restTemplate.postForEntity(url, user, String.class).getBody();
    }

    @PutMapping
    public String editUser( User user) {

        return restTemplate.exchange(url,
                HttpMethod.PUT, new HttpHeaders(Objects.requireNonNull(restTemplate.getForEntity(url,String.class).getHeaders().get("Set-Cookie")).getFirst();), String.class).getBody();
    }
}
