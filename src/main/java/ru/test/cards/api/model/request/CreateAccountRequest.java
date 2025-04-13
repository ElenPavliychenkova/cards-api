package ru.test.cards.api.model.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateAccountRequest {

    private UUID ownerId;
}
