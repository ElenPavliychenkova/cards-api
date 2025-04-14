package ru.test.cards.api.service.card;

import ru.test.cards.api.model.entity.Card;
import ru.test.cards.api.model.request.ChangeCardStatusRequest;
import ru.test.cards.api.model.request.CreateCardRequest;

import java.util.List;
import java.util.UUID;

public interface ICardService {

    List<Card> getAllCards(UUID userId);

    Card getCard(UUID userId, UUID cardId);

    Card createCard(CreateCardRequest createCardRequest);

    void deleteCardById(UUID cardId);

    void changeCardStatus(UUID cardId, ChangeCardStatusRequest changeCardStatusRequest);

    void validateCard(Card card);

    void save(Card card);
}
