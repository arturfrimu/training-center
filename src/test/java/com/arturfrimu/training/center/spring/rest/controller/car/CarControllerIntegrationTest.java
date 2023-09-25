package com.arturfrimu.training.center.spring.rest.controller.car;

import com.arturfrimu.training.center.spring.rest.entity.car.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("db-postgress-test")
class CarControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/car";
    }

    @Test
    @Transactional
    void testAddCar() {
        var newCar = new Car();
        newCar.setId(null);

        var entity = new HttpEntity<>(newCar);

        ResponseEntity<Long> response = restTemplate.postForEntity(baseUrl, entity, Long.class);

        assertEquals(OK, response.getStatusCode());
        assertThat(response.getBody()).isNotNull();
    }
}