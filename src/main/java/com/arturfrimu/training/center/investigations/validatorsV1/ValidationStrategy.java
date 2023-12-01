package com.arturfrimu.training.center.investigations.validatorsV1;

public interface ValidationStrategy<T> {
    boolean validate(T data);
}
