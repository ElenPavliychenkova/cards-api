package ru.test.cards.api.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Data
@Accessors(chain = true)
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "owner_id")
    private UUID ownerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "amount")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @ToString.Exclude
    @Column(name = "number", unique = true)
    private String number;

    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate = LocalDate.now().plusYears(3);

    @Column(name = "cvv", nullable = false)
    @ToString.Exclude
    private String cvv;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.CREATION;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public void addAmount(BigDecimal amount) {

        this.amount = this.amount.add(amount);
    }

    public void subtractAmount(BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }


    public enum Status {
        ACTIVE,
        BLOCKED,
        ISSUED,
        CREATION
    }

    public enum Type {
        DEBIT,
        CREDIT
    }

    public boolean blockCard() {
        if (this.status == Status.ACTIVE) {
            this.status = Status.BLOCKED;
            return true;
        }
        return false;
    }
}
