package ru.test.cards.api.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.cards.api.model.entity.User;

import java.util.UUID;

public interface UserDetailRepository extends JpaRepository<User, UUID> {
}
