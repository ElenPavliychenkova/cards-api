package ru.test.cards.api.exception;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountNotFoundException extends RuntimeException{

    private UUID accountId;

    public AccountNotFoundException(UUID accountId) {
        this.accountId = accountId;
    }
}
