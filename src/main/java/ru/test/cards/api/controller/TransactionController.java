package ru.test.cards.api.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.test.cards.api.model.entity.Transaction;
import ru.test.cards.api.service.transaction.ITransactionService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/transactions")
@RestController
public class TransactionController {

    private final ITransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactionByCardId(@RequestParam UUID cardId) {

        return transactionService.getAllTransactionByCardId(cardId);
    }
}
