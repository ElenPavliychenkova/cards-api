package ru.test.cards.api.exception;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountStatusInactiveException extends RuntimeException {

    private UUID accountId;
    public AccountStatusInactiveException(UUID accountId) {
        this.accountId = accountId;
    }
}
