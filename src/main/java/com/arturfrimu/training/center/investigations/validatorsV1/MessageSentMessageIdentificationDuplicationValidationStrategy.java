package com.arturfrimu.training.center.investigations.validatorsV1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("DUPLICATION_MESSAGE_IDENTIFICATION")
@RequiredArgsConstructor
public class MessageSentMessageIdentificationDuplicationValidationStrategy implements ValidationStrategy<MessageSentEntity> {

    private final MessageSentRepository messageSentRepository;

    @Override
    public boolean validate(MessageSentEntity data) {
        boolean hasDuplicates = messageSentRepository.existsDuplicatesMessageIdentifications(data.getMessageIdentification());

        System.out.println("hasDuplicates : " + hasDuplicates);

        return hasDuplicates;
    }
}
