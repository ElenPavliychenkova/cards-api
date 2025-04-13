package ru.test.cards.api.repository.limit;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.cards.api.model.entity.Limit;

import java.util.List;
import java.util.UUID;

public interface LimitRepository extends JpaRepository<Limit, UUID> {

    List<Limit> findAllByCardId(UUID cardId);
}
