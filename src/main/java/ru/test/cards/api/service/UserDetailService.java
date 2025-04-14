package ru.test.cards.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.test.cards.api.repository.user.UserDetailRepository;
import ru.test.cards.api.security.UserDetailDto;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserDetailRepository userDetailRepository;

    @Override
    public UserDetailDto loadUserByUsername(String email) throws UsernameNotFoundException {

        return userDetailRepository
                .findByEmail(email)
                .map(user -> new UserDetailDto()
                        .setId(user.getId())
                        .setLogin(user.getEmail())
                        .setPassword(user.getPassword())
                        .setRole(user.getRole().name()))
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
