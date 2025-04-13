package ru.test.cards.api.model.request;

import lombok.Data;
import ru.test.cards.api.model.entity.Card;

@Data
public class ChangeCardStatusRequest {

    private Card.Status status;

}
