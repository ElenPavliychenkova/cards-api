package ru.test.cards.api.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.cards.api.model.entity.Transaction;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
