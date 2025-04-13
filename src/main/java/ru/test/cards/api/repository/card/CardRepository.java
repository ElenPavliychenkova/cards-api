package ru.test.cards.api.repository.card;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.cards.api.model.entity.Card;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository <Card, UUID>{

    List<Card> findAllByOwner(UUID ownerId);

    Card findCardByOwnerAndCardId(UUID ownerId, UUID cardId);
}
