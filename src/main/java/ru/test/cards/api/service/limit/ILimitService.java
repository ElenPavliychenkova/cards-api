package ru.test.cards.api.service.limit;


import ru.test.cards.api.model.entity.Limit;
import ru.test.cards.api.model.request.ChangeLimitRequest;
import ru.test.cards.api.model.request.CreateLimitRequest;

import java.util.List;
import java.util.UUID;

public interface ILimitService {


    List<Limit> getAllLimits();

    List<Limit> getAllLimitsByCardId(UUID cardId);

    Limit getLimit(UUID limitId);

    Limit createLimit(CreateLimitRequest createLimitRequest);

    void deleteLimit(UUID limitId);

    void changeLimit(UUID limitId, ChangeLimitRequest changeLimitRequest);

}
