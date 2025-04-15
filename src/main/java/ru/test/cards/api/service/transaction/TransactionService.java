package ru.test.cards.api.service.transaction;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.test.cards.api.exception.ExceedingLimitAmountException;
import ru.test.cards.api.exception.InsufficientFundsException;
import ru.test.cards.api.exception.NoOwnerMatchException;
import ru.test.cards.api.model.entity.Card;
import ru.test.cards.api.model.entity.Currency;
import ru.test.cards.api.model.entity.Limit;
import ru.test.cards.api.model.entity.Transaction;
import ru.test.cards.api.model.request.CashInRequest;
import ru.test.cards.api.model.request.CashOutRequest;
import ru.test.cards.api.model.request.TransferCashRequest;
import ru.test.cards.api.repository.transaction.TransactionRepository;
import ru.test.cards.api.service.card.ICardService;
import ru.test.cards.api.service.limit.ILimitService;
import ru.test.cards.api.util.SecurityUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TransactionService implements ITransactionService {

    public static final List<String> CONSUMABLE_TYPES = List.of(
            Transaction.Type.CARD_TO_CARD.name(),
            Transaction.Type.CASH_OUT.name()
    );

    private final TransactionRepository transactionRepository;

    private final ICardService cardService;

    private final ILimitService limitService;

    @Override
    public List<Transaction> getAllTransactionByCardId(UUID cardId) {

        return transactionRepository.findAllByTargetCardId(cardId);
    }

    @Override
    public void cashIn(CashInRequest request) {
        Card card = cardService.getCard(SecurityUtil.getUserId(), request.getCardId());

        Transaction transaction = createTransaction(
                request.getAmount(),
                Transaction.Type.CASH_IN,
                card.getId(),
                card.getId(),
                card.getCurrency(),
                request.getDescription()
        );
        transactionRepository.save(transaction);


        cardService.validateCard(card);
        card.addAmount(request.getAmount());
        cardService.save(card);


        transaction.setStatus(Transaction.Status.COMPLETED);
        transactionRepository.save(transaction);
    }


    @Override
    public void cashOut(CashOutRequest request) {

        validateLimits(request.getCardId(), request.getAmount());

        Card card = cardService.getCard(SecurityUtil.getUserId(), request.getCardId());
        validateEnoughFunds(card, request.getAmount());
        Transaction transaction = createTransaction(
                request.getAmount(),
                Transaction.Type.CASH_OUT,
                card.getId(),
                card.getId(),
                card.getCurrency(),
                request.getDescription()
        );

        cardService.validateCard(card);
        card.subtractAmount(request.getAmount());
        cardService.save(card);

        transaction.setStatus(Transaction.Status.COMPLETED);
        transactionRepository.save(transaction);
    }

    @Override
    public Transaction transferCardToCard(UUID userId, TransferCashRequest request) {

        Card sourceCard = cardService.getCard(userId, request.getSourceCardId());
        Card targetCard = cardService.getCard(userId, request.getTargetCardId());

        if (sourceCard == null || targetCard == null) {
            throw new NoOwnerMatchException();
        }
        cardService.validateCard(sourceCard);
        cardService.validateCard(targetCard);
        validateEnoughFunds(sourceCard, request.getAmount());
        validateLimits(request.getSourceCardId(), request.getAmount());

        Transaction transaction = createTransaction(
                request.getAmount(),
                Transaction.Type.CARD_TO_CARD,
                sourceCard.getId(),
                targetCard.getId(),
                sourceCard.getCurrency(),
                request.getDescription()
        );
        sourceCard.subtractAmount(request.getAmount());
        cardService.save(sourceCard);

        targetCard.addAmount(request.getAmount());
        cardService.save(targetCard);

        transaction.setStatus(Transaction.Status.COMPLETED);
        return transactionRepository.save(transaction);

    }

    private void validateLimits(UUID cardId, BigDecimal amount) {

        limitService.getAllLimitsByCardId(cardId).forEach(limit -> {
            BigDecimal transactionsSum = findGetSumOfTransaction(cardId, limit.getType());
            if (transactionsSum.add(amount).compareTo(limit.getAmount()) > 0) {
                throw new ExceedingLimitAmountException(cardId, limit.getType(), amount);
            }
        });
    }

    private Transaction createTransaction(BigDecimal amount, Transaction.Type type, UUID sourceCardId, UUID targetCardId, Currency currency, String description) {

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setSourceCardId(sourceCardId);
        transaction.setTargetCardId(targetCardId);
        transaction.setCurrency(currency);
        transaction.setCreated(LocalDateTime.now());
        transaction.setUpdated(LocalDateTime.now());
        transaction.setDescription(description);

        return transactionRepository.save(transaction);
    }

    private void validateEnoughFunds(Card sourceCard, BigDecimal amount) {
        if (sourceCard.getAmount().compareTo(amount) < 0) {
            throw new InsufficientFundsException(sourceCard.getId());
        }
    }

    private BigDecimal findGetSumOfTransaction(UUID cardId, Limit.LimitType limitType) {

        final LocalDateTime now = LocalDateTime.now();
        return switch (limitType) {
            case DAY -> transactionRepository.getSumTransactionByCardIdAndTypeAndPeriod(cardId, CONSUMABLE_TYPES, LocalDate.now().atStartOfDay(), now);
            case MONTH -> transactionRepository.getSumTransactionByCardIdAndTypeAndPeriod(cardId, CONSUMABLE_TYPES, now.minusDays(30), now);
        };
    }
}
