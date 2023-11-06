package com.arturfrimu.training.center.jasper.x001;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.RequestEntity.get;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SimpleJasperReportControllerTest {

    @LocalServerPort
    private String port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SimpleJasperReportController.ReportService reportService;

    @Test
    void getOrders() {
        String URL = "http://localhost:" + port + "/jasper/simple-report";
        ResponseEntity<List<SimpleJasperReportController.Order>> orders = restTemplate.exchange(get(URL).build(), ORDERS);

        assertThat(orders).isNotNull();
        assertThat(orders.getBody()).hasSize(5);

        orders.getBody().forEach(System.out::println);
    }

    @ValueSource(strings = {"html", "pdf"})
    @ParameterizedTest
    void generateReport(final String reportFormat) {
        String response = new SimpleJasperReportController(reportService).generateReport(reportFormat, "jasper/order.jrxml");

        assertThat(response).isNotNull();
    }

    static final ParameterizedTypeReference<List<SimpleJasperReportController.Order>> ORDERS = new ParameterizedTypeReference<>() {
    };
}