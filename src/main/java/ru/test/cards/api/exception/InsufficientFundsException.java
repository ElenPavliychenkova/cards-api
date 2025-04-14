package ru.test.cards.api.exception;

import lombok.Data;

import java.util.UUID;

@Data
public class InsufficientFundsException extends RuntimeException {

    private UUID id;

    public InsufficientFundsException(UUID id) {
        this.id = id;
    }
}
