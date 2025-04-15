package ru.test.cards.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse<T> {

    private T data;

    private String message;

    private LocalDateTime time = LocalDateTime.now();
}
