package ru.test.cards.api.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Slf4j
@Entity
@Table(name = "transactions")
@Accessors(chain = true)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "source_card_id")
    private UUID sourceCardId;

    @Column(name = "target_card_id")
    private UUID targetCardId;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Column(name = "created_at")
    private LocalDateTime created;

    @Column(name = "updated_ed")
    private LocalDateTime updated;

    @Column(name = "type")
    private Type type;

    public enum Type {
        CARD_TO_CARD,
        CASH_IN,
        CASH_OUT
    }

    public enum Status {
        PENDING,
        CANCELED,
        COMPLETED,
        FAILED;
    }

}
