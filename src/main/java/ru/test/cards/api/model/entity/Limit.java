package ru.test.cards.api.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@Table(name = "limits")
@Slf4j
@Accessors(chain = true)
@Entity
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "used_amount")
    private BigDecimal usedAmount = BigDecimal.ZERO;

    private UUID cardId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private LimitType type;

    public enum LimitType  {
        DAY,
        MONTH
    }
}
