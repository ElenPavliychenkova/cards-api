package ru.test.cards.api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

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
    private UUID cardId;

    @Column(name = "owner_id")
    private UUID ownerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ToString.Exclude
    @Column(name = "number", unique = true)
    private String number;

    @Column(name = "issue_date", nullable = false)
    @PastOrPresent
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
