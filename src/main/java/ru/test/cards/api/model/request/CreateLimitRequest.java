package ru.test.cards.api.model.request;

import lombok.Data;
import ru.test.cards.api.model.entity.Limit;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreateLimitRequest {

    private UUID cardId;

    private BigDecimal amount;

    private Limit.LimitType type;
}
