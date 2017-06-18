package com.bg.controller;


import com.bg.messaging.Receiver;
import com.bg.messaging.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class HelloWorldController {

    @Autowired
    Sender sender;


    @Autowired
    Receiver receiver;


    @RequestMapping("/greetings")
    public String getGreetings() {
        return "Hello World";
    }

    @RequestMapping("/callInternalGreetings")
    public String callInternalGreetings() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(new URI("http://localhost:8080/internal-greetings"), String.class);
        return forEntity.getBody();
    }

    @RequestMapping("/callRabbitMq")
    public String callRabbitMq() throws URISyntaxException, InterruptedException {
        sender.send("hello");
        return receiver.getLatest();
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> container.setPort(80);
    }
}
