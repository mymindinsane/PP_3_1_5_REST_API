package com.kata.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.kata.restapi")
public class SpringConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
