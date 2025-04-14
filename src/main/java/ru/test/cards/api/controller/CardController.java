package ru.test.cards.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.test.cards.api.model.entity.Card;
import ru.test.cards.api.service.card.ICardService;
import ru.test.cards.api.util.SecurityUtil;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер CRUD операций с карточками.
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cards")
@RestController
@PreAuthorize("hasRole('USER')")
public class CardController {

    private final ICardService cardService;

    @GetMapping
    public List<Card> getAllCards() {

        return cardService.getAllCards(SecurityUtil.getUserId());
    }

    @GetMapping("/{cardId}")
    public Card getCard(@PathVariable UUID cardId) {

        return cardService.getCard(SecurityUtil.getUserId(), cardId);
    }
}
