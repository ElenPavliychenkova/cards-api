package ru.test.cards.api.exception;

import lombok.Data;
import ru.test.cards.api.model.entity.Limit;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ExceedingLimitAmountException extends RuntimeException {

    private UUID cardId;
    private Limit.LimitType type;
    private BigDecimal amount;

    public ExceedingLimitAmountException(UUID cardId, Limit.LimitType type, BigDecimal amount) {
        this.cardId = cardId;
        this.type = type;
        this.amount = amount;
    }
}
