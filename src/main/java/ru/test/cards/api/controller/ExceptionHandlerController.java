package ru.test.cards.api.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.test.cards.api.exception.*;
import ru.test.cards.api.model.response.ErrorResponse;

import java.util.UUID;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(CardNotFoundException.class)
    public ErrorResponse<UUID> handle(CardNotFoundException exception) {

        return new ErrorResponse<UUID>()
                .setData(exception.getCardId())
                .setMessage(exception.getClass().getName());
    }

    @ExceptionHandler(CardStatusInactiveException.class)
    public ErrorResponse<UUID> handle(CardStatusInactiveException exception) {

        return new ErrorResponse<UUID>()
                .setData(exception.getCardId())
                .setMessage(exception.getClass().getName());
    }

    @ExceptionHandler(ExceedingLimitAmountException.class)
    public ErrorResponse<UUID> handle(ExceedingLimitAmountException exception) {

        return new ErrorResponse<UUID>()
                .setData(exception.getCardId())
                .setMessage(exception.getClass().getName());
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ErrorResponse<UUID> handle(InsufficientFundsException exception) {

        return new ErrorResponse<UUID>()
                .setData(exception.getId())
                .setMessage(exception.getClass().getName());
    }

    @ExceptionHandler(LimitNotFoundException.class)
    public ErrorResponse<UUID> handle(LimitNotFoundException exception) {

        return new ErrorResponse<UUID>()
                .setData(exception.getLimitId())
                .setMessage(exception.getClass().getName());
    }

    @ExceptionHandler(NoOwnerMatchException.class)
    public ErrorResponse<String> handle(NoOwnerMatchException exception) {

        return new ErrorResponse<String>()
                .setData("Карты не принадлежат одному пользователю")
                .setMessage(exception.getClass().getName());
    }
}
