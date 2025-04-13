package ru.test.cards.api.service.transaction;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.test.cards.api.exception.CardStatusInactiveException;
import ru.test.cards.api.model.entity.Account;
import ru.test.cards.api.model.entity.Card;
import ru.test.cards.api.model.entity.Currency;
import ru.test.cards.api.model.entity.Transaction;
import ru.test.cards.api.model.request.CashInRequest;
import ru.test.cards.api.repository.transaction.TransactionRepository;
import ru.test.cards.api.service.account.IAccountService;
import ru.test.cards.api.service.card.ICardService;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    private final ICardService cardService;
    private final IAccountService accountService;

    public Transaction createTransaction(BigDecimal amount, Transaction.Type type, UUID sourceCardId, UUID targetCardId, Currency currency, String description) {

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setSourceCardId(sourceCardId);
        transaction.setTargetCardId(targetCardId);
        transaction.setCurrency(currency);
        transaction.setDescription(description);

        return transactionRepository.save(transaction);
    }

    @Override
    public void cashIn(CashInRequest request) {

        Card card = cardService.getCard(request.getUserId(), request.getCardId());
        cardService.validateCard(card);

        Account account = accountService.getAccount(card.getAccountId());
        if (!account.getStatus().equals(Account.Status.ACTIVE)) {
            throw new CardStatusInactiveException(card.getCardId());
        }

        Transaction transaction = createTransaction(request.getAmount(), Transaction.Type.CASH_IN, null, card.getCardId(), account.getCurrency(), request.getDescription());

        account.addAmount(request.getAmount());
        accountService.save(account);

        transaction.setStatus(Transaction.Status.COMPLETED);
    }
}
