package com.arturfrimu.training.center.investigations.validatorsV1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("MESSAGE_SENDER_NOT_THE_SAME_AS_MESSAGE_RECIPIENT")
@RequiredArgsConstructor
public class MessageSentMessageSenderNotTheSameAsMessageRecipientValidationStrategy implements ValidationStrategy<MessageSentEntity> {

    @Override
    public boolean validate(MessageSentEntity data) {
        boolean senderEqualsWithRecipient = !Objects.equals(data.getMessageSender(), data.getMessageRecipient());

        System.out.println("senderEqualsWithRecipient : " + senderEqualsWithRecipient);

        return senderEqualsWithRecipient;
    }
}
