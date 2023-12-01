package com.arturfrimu.training.center.investigations.validatorsV1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientTest {

    @Autowired
    private Client client;

    @Test
    void sendMessage() {
        boolean wasSent = client.sendMessage(
                new MessageSentEntity(
                        UUID.randomUUID(),
                        "",
                        LocalDateTime.now(),
                        "CD500A",
                        "RO",
                        "MD",
                        "",
                        "",
                        "0000",
                        "",
                        ""
                ));

        assertTrue(wasSent);
    }
}