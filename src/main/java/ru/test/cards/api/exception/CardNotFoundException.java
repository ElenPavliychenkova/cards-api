package ru.test.cards.api.exception;

import lombok.Data;

import java.util.UUID;

@Data
public class CardNotFoundException extends RuntimeException{

    private UUID cardId;

    public CardNotFoundException(UUID cardId) {
        this.cardId = cardId;
    }
}
