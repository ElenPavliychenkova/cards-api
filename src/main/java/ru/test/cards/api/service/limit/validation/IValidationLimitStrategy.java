package ru.test.cards.api.service.limit.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.test.cards.api.model.entity.Account;
import ru.test.cards.api.model.entity.Card;
import ru.test.cards.api.model.entity.Limit;
import ru.test.cards.api.service.account.IAccountService;
import ru.test.cards.api.service.card.ICardService;
import ru.test.cards.api.service.limit.ILimitService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class IValidationLimitStrategy {

    private final ICardService cardService;
    private final ILimitService limitService;
    private final IAccountService accountService;

    public boolean validate(UUID cardId, BigDecimal amount) {

        List<Limit> limits = limitService.getAllLimitsByCardId(cardId);

        Card card = cardService.getCard(null, cardId);
        Account account = accountService.getAccount(card.getAccountId());



        return false;
    }
}
