package ru.test.cards.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.test.cards.api.model.entity.Transaction;
import ru.test.cards.api.model.request.TransferCashRequest;
import ru.test.cards.api.service.transaction.ITransactionService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/transfers")
@RestController
public class TransferController {

    private final ITransactionService transactionService;


    @PostMapping("/{userId}")
    public Transaction cardToCard(@RequestParam UUID userId, TransferCashRequest request) {
        return transactionService.transferCardToCard(userId, request);
    }
}
