package ru.test.cards.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.test.cards.api.model.entity.Limit;
import ru.test.cards.api.model.request.ChangeLimitRequest;
import ru.test.cards.api.model.request.CreateLimitRequest;
import ru.test.cards.api.service.limit.ILimitService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/limits")
@RestController
@PreAuthorize("hasRole('ADMIN')")
public class LimitController {

    private final ILimitService limitService;

    @GetMapping
    public List<Limit> getAllLimits(@RequestParam UUID cardId) {

        return limitService.getAllLimitsByCardId(cardId);
    }

    @GetMapping("/{limitId}")
    public Limit getLimit(@PathVariable UUID limitId) {

        return limitService.getLimit(limitId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Limit creatLimit(@RequestBody CreateLimitRequest createLimitRequest) {

        return limitService.createLimit(createLimitRequest);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{limitId}")
    public void deleteLimit(@PathVariable UUID limitId) {

        limitService.deleteLimit(limitId);
    }

    @PutMapping("/{limitId}")
    public void changeLimit(@PathVariable UUID limitId, @RequestBody ChangeLimitRequest changeLimitRequest) {

        limitService.changeLimit(limitId, changeLimitRequest);
    }

}
