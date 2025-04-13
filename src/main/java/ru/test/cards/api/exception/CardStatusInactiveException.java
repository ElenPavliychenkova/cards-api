package ru.test.cards.api.exception;

import lombok.Data;

import java.util.UUID;

@Data
public class CardStatusInactiveException extends RuntimeException {

    private UUID cardId;
    public CardStatusInactiveException(UUID cardId) {
        this.cardId = cardId;
    }
}
