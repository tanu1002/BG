package com.bg.controller;


import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class HelloManagementController {

    @RequestMapping("/internal-greetings")
    public String getInternalGreetings() {
        return "Hello Management";
    }

    @RequestMapping("/callGreetings")
    public String callGreetings() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(new URI("http://localhost:80/greetings"), String.class);
        return forEntity.getBody();
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
         return container -> container.setPort(8080);
    }
}
