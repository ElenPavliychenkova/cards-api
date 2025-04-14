package ru.test.cards.api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.test.cards.api.security.UserDetailDto;

import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtil {

    public static UUID getUserId() {

        return Optional
                .ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(principal -> (UserDetailDto) principal)
                .map(UserDetailDto::getId)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
