package com.ratracks.domain.usecases;

public interface UseCase<I, O> {
    O execute(I input);
}
