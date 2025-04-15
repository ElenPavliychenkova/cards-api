package ru.test.cards.api.model.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransferCashRequest {

    private UUID sourceCardId;

    private UUID targetCardId;

    private BigDecimal amount;

    private String description;
}
