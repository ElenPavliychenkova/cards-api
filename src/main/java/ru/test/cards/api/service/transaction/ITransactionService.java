package ru.test.cards.api.service.transaction;

import ru.test.cards.api.model.entity.Transaction;
import ru.test.cards.api.model.request.CashInRequest;
import ru.test.cards.api.model.request.CashOutRequest;
import ru.test.cards.api.model.request.TransferCashRequest;

import java.util.List;
import java.util.UUID;

public interface ITransactionService {

    void cashIn(CashInRequest request);

    void cashOut(CashOutRequest request);

    Transaction transferCardToCard(UUID userId, TransferCashRequest request);

    List<Transaction> getAllTransactionByCardId(UUID cardId);
}
