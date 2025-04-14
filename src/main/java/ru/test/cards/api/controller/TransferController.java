package ru.test.cards.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.test.cards.api.model.entity.Transaction;
import ru.test.cards.api.model.request.TransferCashRequest;
import ru.test.cards.api.service.transaction.ITransactionService;
import ru.test.cards.api.util.SecurityUtil;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/transfers")
@RestController
@PreAuthorize("hasRole('USER')")
public class TransferController {

    private final ITransactionService transactionService;

    @PostMapping
    public Transaction transferCardToCard(@RequestBody TransferCashRequest request) {

        return transactionService.transferCardToCard(SecurityUtil.getUserId(), request);
    }
}
