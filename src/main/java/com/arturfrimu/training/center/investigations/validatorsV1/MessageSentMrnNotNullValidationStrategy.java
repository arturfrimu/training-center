package com.arturfrimu.training.center.investigations.validatorsV1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("MRN_NOT_NULL")
@RequiredArgsConstructor
public class MessageSentMrnNotNullValidationStrategy implements ValidationStrategy<MessageSentEntity> {

    @Override
    public boolean validate(MessageSentEntity data) {
        boolean nullMrn = Objects.nonNull(data.getMrn());

        System.out.println("nullMrn : " + nullMrn);

        return nullMrn;
    }
}
