package ru.test.cards.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.test.cards.api.model.request.AuthRequest;
import ru.test.cards.api.security.JwtTokenService;

@Slf4j
@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userService;

    private final JwtTokenService tokenService;

    @PostMapping
    public String getToken(@Valid @RequestBody AuthRequest request) {

        var authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authenticationManager.authenticate(authentication);

        UserDetails userDetails = userService.loadUserByUsername(request.getEmail());

        return tokenService.generateToken(userDetails);
    }
}