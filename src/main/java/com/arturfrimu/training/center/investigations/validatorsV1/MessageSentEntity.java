package com.arturfrimu.training.center.investigations.validatorsV1;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class MessageSentEntity {
    private UUID id;
    private String message;
    private LocalDateTime createDate;
    private String status;
    private String messageSender;
    private String messageRecipient;
    private String messageIdentification;
    private String messageType;
    private String mrn;
    private String errorDetails;
    private String domain;

    public MessageSentEntity(UUID id,
                             String message,
                             LocalDateTime createDate,
                             String status,
                             String messageSender,
                             String messageRecipient,
                             String messageIdentification,
                             String messageType,
                             String mrn,
                             String errorDetails,
                             String domain) {
        this.id = id;
        this.message = message;
        this.createDate = createDate;
        this.status = status;
        this.messageSender = messageSender;
        this.messageRecipient = messageRecipient;
        this.messageIdentification = messageIdentification;
        this.messageType = messageType;
        this.mrn = mrn;
        this.errorDetails = errorDetails;
        this.domain = domain;
    }
}
