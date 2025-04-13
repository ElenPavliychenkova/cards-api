package ru.test.cards.api.service.cash;

import ru.test.cards.api.model.request.CashInRequest;
import ru.test.cards.api.model.request.CashOutRequest;

public interface ICashService {
    void out(CashOutRequest request);

    void in(CashInRequest request);
}
