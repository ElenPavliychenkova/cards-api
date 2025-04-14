package ru.test.cards.api.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.test.cards.api.model.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query(
            value = """
                    SELECT sum(amount)  
                    FROM transactions
                    WHERE target_card_id = :cardId 
                        AND type IN (:types)
                        AND (created >= :from AND created <= :to)
                        AND status = 'COMPLETED'
                    """,
            nativeQuery = true
    )
    BigDecimal getSumTransactionByCardIdAndTypeAndPeriod(UUID cardId, List<Transaction.Type> types, LocalDateTime from, LocalDateTime to);
}
