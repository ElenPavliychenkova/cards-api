package ru.test.cards.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.test.cards.api.model.entity.Card;
import ru.test.cards.api.model.request.ChangeCardStatusRequest;
import ru.test.cards.api.model.request.CreateCardRequest;
import ru.test.cards.api.service.card.ICardService;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер CRUD операций с карточками.
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cards")
@RestController
public class CardController {

    private final ICardService cardService;

    @GetMapping
    public List<Card> getAllCards(@RequestParam UUID userId) {

        return cardService.getAllCards(userId);
    }

    @GetMapping("/{cardId}")
    public Card getCard(@PathVariable UUID cardId, @RequestParam UUID userId) {

        return cardService.getCard(userId, cardId);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Card createCard(@RequestBody CreateCardRequest createCardRequest) {

        return cardService.createCard(createCardRequest);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{cardId}")
    public void deleteCard(@PathVariable UUID cardId) {

        cardService.deleteCardById(cardId);
    }

    @PutMapping("/{cardId}")
    public void changeCardStatus(@PathVariable UUID cardId, @RequestBody ChangeCardStatusRequest changeCardStatusRequest) {

        cardService.changeCardStatus(cardId, changeCardStatusRequest);
    }


}
