package ru.test.cards.api.service.limit.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.test.cards.api.exception.ExceedingLimitAmountException;
import ru.test.cards.api.service.card.ICardService;
import ru.test.cards.api.service.limit.ILimitService;
import ru.test.cards.api.service.transaction.ITransactionService;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class IValidationLimitStrategy {

    private final ILimitService limitService;
    private final ITransactionService transactionService;


    public void validate(UUID cardId, BigDecimal amount) {

        limitService.getAllLimitsByCardId(cardId).forEach(limit -> {
            BigDecimal transactionsSum = transactionService.findGetSumOfTransaction(cardId, limit.getType());
            if (transactionsSum.add(amount).compareTo(limit.getAmount()) > 0) {
                throw new ExceedingLimitAmountException(cardId, limit.getType(), amount);
            }
        });
    }
}
