package ru.test.cards.api.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.cards.api.model.entity.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByIdAndOwnerId(UUID accountId, UUID ownerId);
}
