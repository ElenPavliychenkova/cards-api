package ru.test.cards.api.service.cash;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.test.cards.api.model.request.CashInRequest;
import ru.test.cards.api.model.request.CashOutRequest;
import ru.test.cards.api.service.transaction.ITransactionService;

@Slf4j
@Service
@RequiredArgsConstructor
public class CashService implements ICashService {

    private final ITransactionService transactionService;

    @Override
    public void out(CashOutRequest request) {

        transactionService.cashOut(request);
    }

    @Override
    public void in(CashInRequest request) {

        transactionService.cashIn(request);
    }

}
