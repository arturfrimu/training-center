package com.arturfrimu.training.center.investigations.validatorsV1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("MRN_NOT_EMPTY")
@RequiredArgsConstructor
public class MessageSentMrnNotEmptyValidationStrategy implements ValidationStrategy<MessageSentEntity> {

    @Override
    public boolean validate(MessageSentEntity data) {
        boolean emptyMrn = Objects.nonNull(data.getMrn()) && !data.getMrn().isEmpty();

        System.out.println("emptyMrn : " + emptyMrn);

        return emptyMrn;
    }
}
