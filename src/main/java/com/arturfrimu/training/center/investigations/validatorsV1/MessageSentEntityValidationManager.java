package com.arturfrimu.training.center.investigations.validatorsV1;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MessageSentEntityValidationManager {

    private final Map<String, ValidationStrategy<MessageSentEntity>> validationsMap;
    private final Map<String, Set<ValidationStrategy<MessageSentEntity>>> validationsMapPerMessageType = new HashMap<>();

    @PostConstruct
    public void initialize() {
        validationsMapPerMessageType.put("CD500A", Set.of(
                validationsMap.get("MRN_NOT_EMPTY"),
                validationsMap.get("MESSAGE_SENDER_NOT_THE_SAME_AS_MESSAGE_RECIPIENT")
        ));
    }

    public boolean validate(MessageSentEntity data) {
        Set<ValidationStrategy<MessageSentEntity>> validators = validationsMapPerMessageType
                .getOrDefault(data.getStatus(), Collections.emptySet());

        return validators
                .stream()
                .allMatch(validation -> validation.validate(data));
    }

    public enum ValidationBeanNames {
        DUPLICATION_MESSAGE_IDENTIFICATION,
        MRN_NOT_NULL,
        MRN_NOT_EMPTY,
        MESSAGE_SENDER_NOT_THE_SAME_AS_MESSAGE_RECIPIENT,
    }
}
