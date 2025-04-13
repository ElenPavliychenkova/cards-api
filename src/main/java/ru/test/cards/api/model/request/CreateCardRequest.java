package ru.test.cards.api.model.request;

import lombok.Data;
import ru.test.cards.api.model.entity.Card;


import java.util.UUID;

@Data
public class CreateCardRequest {

  private UUID userId;

  private String firstName;

  private String lastName;

  private Card.Type type;

  /**
   * Необязательный параметр.
   */
  private UUID accountId;

}
