package com.bg.controller.integration;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HelloManagementControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getInternalGreetingsTest() throws URISyntaxException {
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(new URI("http://localhost:8080/internal-greetings"), String.class);
        assertThat(HttpStatus.OK, Is.is(responseEntity.getStatusCode()));
        assertThat("Hello Management", Is.is(responseEntity.getBody()));
    }

    @Test
    public void callGreetingsTest() throws URISyntaxException {
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(new URI("http://localhost:8080/callGreetings"), String.class);
        assertThat(HttpStatus.OK, Is.is(responseEntity.getStatusCode()));
        assertThat("Hello World", Is.is(responseEntity.getBody()));
    }

}