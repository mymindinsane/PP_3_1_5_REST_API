package com.kata.restapi.controller;

import com.kata.restapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Objects;

@RestController
public class Connection {

    private final RestTemplate restTemplate;
    private final String cookie;

    final String url = "http://94.198.50.185:7081/api/users/";

    @Autowired
    public Connection(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        cookie = getCookie();
    }

    public String getCookie() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        return Objects.requireNonNull(forEntity.getHeaders().get("Set-Cookie")).getFirst();
    }

    @GetMapping
    public String getAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    }

    @PostMapping
    public String saveUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(
                url, HttpMethod.POST, entity, String.class).getBody();
    }

    @PutMapping
    public String updateUser(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        return restTemplate.exchange(
                url, HttpMethod.PUT, entity, String.class).getBody();
    }

    @DeleteMapping(value = "/template/products/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(
                url + id, HttpMethod.DELETE, entity, String.class).getBody();
    }
}


