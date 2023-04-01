package com.ratracks.domain.usecases;

public interface UseCase<Input, Output> {
    Output execute(Input input);
}
