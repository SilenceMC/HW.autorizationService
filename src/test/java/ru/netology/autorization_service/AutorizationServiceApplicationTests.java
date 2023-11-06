package ru.netology.autorization_service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AutorizationServiceApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    private final GenericContainer<?> devApp= new GenericContainer<>("devapp:latest").withExposedPorts(8080);
    @Container
    private final GenericContainer<?> prodApp= new GenericContainer<>("prodapp:latest").withExposedPorts(8080);


    @Test
    void devStatusNotFoundTest(){
        //arrange
        HttpStatusCode expectedStatusCode = HttpStatusCode.valueOf(404);

        //act
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(8080), String.class);
        HttpStatusCode resultStatusCode = forEntity.getStatusCode();

        //assert
        Assertions.assertEquals(expectedStatusCode,resultStatusCode);
    }

    @Test
    void prodStatusUnauthorizedTest(){
        //arrange
        String randomUserName = "fhgbfhg";
        String randomUserPassword = "jfndgfdg";

        //act
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + prodApp.getMappedPort(8080) +
                "/authorize?user=" + randomUserName +
                "&password=" + randomUserPassword, String.class);
        HttpStatusCode resultStatusCode = forEntity.getStatusCode();

        //assert
        Assertions.assertEquals(resultStatusCode, HttpStatus.UNAUTHORIZED);
    }

}
