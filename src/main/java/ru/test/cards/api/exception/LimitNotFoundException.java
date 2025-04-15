package ru.test.cards.api.exception;

import lombok.Data;

import java.util.UUID;

@Data
public class LimitNotFoundException extends RuntimeException{

    private UUID limitId;

    public LimitNotFoundException(UUID limitId) {
        this.limitId = limitId;
    }
}
