package ru.test.cards.api.service.transaction;

import ru.test.cards.api.model.entity.Limit;
import ru.test.cards.api.model.entity.Transaction;
import ru.test.cards.api.model.request.CashInRequest;
import ru.test.cards.api.model.request.CashOutRequest;
import ru.test.cards.api.model.request.TransferCashRequest;

import java.math.BigDecimal;
import java.util.UUID;

public interface ITransactionService {
    void cashIn(CashInRequest request);

    BigDecimal findGetSumOfTransaction(UUID cardId, Limit.LimitType limitType);

    void cashOut(CashOutRequest request);

    Transaction transferCardToCard(UUID userId, TransferCashRequest request);
}
