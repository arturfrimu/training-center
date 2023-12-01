package com.arturfrimu.training.center.investigations.validatorsV1;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class MessageSentRepository {
    private static final Map<UUID, MessageSentEntity> messages = new HashMap<>();

    public MessageSentEntity get(UUID id) {
        return messages.get(id);
    }

    public MessageSentEntity put(MessageSentEntity messageSentEntity) {
        return messages.put(messageSentEntity.getId(), messageSentEntity);
    }

    public boolean existsDuplicatesMessageIdentifications(String messageIdentification) {
        return messages.values().stream().anyMatch(m -> m.getMessageIdentification().equals(messageIdentification));
    }
}
