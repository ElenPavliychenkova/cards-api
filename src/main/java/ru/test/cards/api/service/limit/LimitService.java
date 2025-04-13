package ru.test.cards.api.service.limit;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.test.cards.api.exception.LimitNotFoundException;
import ru.test.cards.api.model.entity.Limit;
import ru.test.cards.api.model.request.ChangeLimitRequest;
import ru.test.cards.api.model.request.CreateLimitRequest;
import ru.test.cards.api.repository.limit.LimitRepository;

import java.util.List;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class LimitService implements ILimitService {

    private final LimitRepository limitRepository;

    @Override
    public List<Limit> getAllLimits() {

        return limitRepository.findAll();
    }

    @Override
    public List<Limit> getAllLimitsByCardId(UUID cardId) {

        return limitRepository.findAllByCardId(cardId);
    }

    @Override
    public Limit getLimit(UUID limitId) {

        return limitRepository.findById(limitId)
                .orElseThrow(() -> new LimitNotFoundException(limitId));
    }

    @Override
    public Limit createLimit(CreateLimitRequest createLimitRequest) {
        return null;
    }

    @Override
    public void deleteLimit(UUID limitId) {
        getLimit(limitId);
        limitRepository.deleteById(limitId);
    }

    @Override
    public void changeLimit(UUID limitId, ChangeLimitRequest changeLimitRequest) {

        Limit limit = getLimit(limitId);
        limit.setAmount(changeLimitRequest.getAmount());
        limitRepository.save(limit);
    }
}