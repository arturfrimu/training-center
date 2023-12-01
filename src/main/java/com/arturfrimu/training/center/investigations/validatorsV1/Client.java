package com.arturfrimu.training.center.investigations.validatorsV1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Client {

    private final MessageSentEntityValidationManager messageSentEntityValidationManager;

    public boolean sendMessage(MessageSentEntity messageSentEntity) {
        boolean validate = messageSentEntityValidationManager.validate(messageSentEntity);

        log.info("Send message here !!!");

        return validate;
    }
}
