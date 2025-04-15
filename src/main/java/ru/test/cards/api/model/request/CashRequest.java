package ru.test.cards.api.model.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public abstract class CashRequest {


    private UUID cardId;

    private BigDecimal amount;

    private String description;
}
