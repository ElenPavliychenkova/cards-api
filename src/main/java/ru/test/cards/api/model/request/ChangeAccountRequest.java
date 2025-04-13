package ru.test.cards.api.model.request;

import lombok.Data;
import ru.test.cards.api.model.entity.Account;

@Data
public class ChangeAccountRequest {

    private Account.Status status;
}
