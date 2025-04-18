package ru.test.cards.api.service.card;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.test.cards.api.exception.CardNotFoundException;
import ru.test.cards.api.exception.CardStatusInactiveException;
import ru.test.cards.api.model.entity.Card;
import ru.test.cards.api.model.entity.Currency;
import ru.test.cards.api.model.request.ChangeCardStatusRequest;
import ru.test.cards.api.model.request.CreateCardRequest;
import ru.test.cards.api.repository.card.CardRepository;
import ru.test.cards.api.util.CardUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService implements ICardService {

    private final CardRepository cardRepository;

    @Override
    public List<Card> getAllCards(UUID userId) {

        return cardRepository.findAllByOwnerId(userId);
    }

    @Override
    public Card getCard(UUID userId, UUID cardId) {
        return cardRepository.findByIdAndOwnerId(cardId, userId);
    }

    @Override
    public Card createCard(CreateCardRequest createCardRequest) {

        Card card = new Card();
        card.setFirstName(createCardRequest.getFirstName());
        card.setLastName(createCardRequest.getLastName());
        card.setAmount(BigDecimal.ZERO);
        card.setCurrency(Currency.RUB);
        card.setType(createCardRequest.getType());
        card.setOwnerId(createCardRequest.getUserId());
        card.setNumber(CardUtil.generateCardNumber());
        card.setCvv(CardUtil.generateCvv());
        card.setCreatedAt(LocalDateTime.now());

        return cardRepository.save(card);
    }

    @Override
    public void deleteCardById(UUID cardId) {

        cardRepository
                .findById(cardId)
                .orElseThrow(() -> new CardNotFoundException(cardId));
        cardRepository.deleteById(cardId);
    }

    @Override
    public void changeCardStatus(UUID cardId, ChangeCardStatusRequest changeCardStatusRequest) {

        Card card = cardRepository
                .findById(cardId)
                .orElseThrow(() -> new CardNotFoundException(cardId));
        card.setStatus(changeCardStatusRequest.getStatus());
        cardRepository.save(card);
    }

    @Override
    public void validateCard(Card card) {
        if (!card.getStatus().equals(Card.Status.ACTIVE)) {
            throw new CardStatusInactiveException(card.getId());
        }
    }

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }
}
