package ru.test.cards.api.service.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.test.cards.api.repository.user.UserDetailRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService implements IAuthService{

    private final UserDetailRepository userDetailRepository;
}
