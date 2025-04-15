package ru.test.cards.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.test.cards.api.model.request.CashInRequest;
import ru.test.cards.api.model.request.CashOutRequest;
import ru.test.cards.api.service.cash.CashService;

/**
 * Контроллер CRUD операций с карточками.
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cash")
@RestController
@PreAuthorize("hasAuthority('USER')")
public class CashController {

    private final CashService cashService;

    @PostMapping("/in")
    public void in(@RequestBody CashInRequest request) {
        cashService.in(request);
    }

    @PostMapping("/out")
    public void out(@RequestBody CashOutRequest request) {

        cashService.out(request);
    }
}
