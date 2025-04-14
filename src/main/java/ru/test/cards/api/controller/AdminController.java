package ru.test.cards.api.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.test.cards.api.model.entity.Card;
import ru.test.cards.api.model.entity.Transaction;
import ru.test.cards.api.model.request.ChangeCardStatusRequest;
import ru.test.cards.api.model.request.CreateCardRequest;
import ru.test.cards.api.service.card.ICardService;
import ru.test.cards.api.service.transaction.ITransactionService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final ICardService cardService;
    private final ITransactionService transactionService;


    @GetMapping("/cards/{cardId}")
    public Card getCard(@PathVariable UUID cardId, @RequestParam UUID userId) {

        return cardService.getCard(userId, cardId);
    }

    @GetMapping("/cards")
    public List<Card> getAllCards(@RequestParam UUID userId) {

        return cardService.getAllCards(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cards")
    public Card createCard(@RequestBody CreateCardRequest createCardRequest) {

        return cardService.createCard(createCardRequest);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/cards/{cardId}")
    public void deleteCard(@PathVariable UUID cardId) {

        cardService.deleteCardById(cardId);
    }

    @PutMapping("/cards/{cardId}")
    public void changeCardStatus(@PathVariable UUID cardId, @RequestBody ChangeCardStatusRequest changeCardStatusRequest) {

        cardService.changeCardStatus(cardId, changeCardStatusRequest);
    }

}
