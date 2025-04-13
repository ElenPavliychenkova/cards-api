package ru.test.cards.api.service.transaction;

import ru.test.cards.api.model.request.CashInRequest;

public interface ITransactionService {
    void cashIn(CashInRequest request);

}
