package ru.test.cards.api.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
public class AuthRequest {

    @NotBlank
    private String email;

    @NotBlank
    @ToString.Exclude
    private String password;
}
