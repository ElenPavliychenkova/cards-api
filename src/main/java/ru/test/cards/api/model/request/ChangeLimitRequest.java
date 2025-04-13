package ru.test.cards.api.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChangeLimitRequest {

    private BigDecimal amount;
}
