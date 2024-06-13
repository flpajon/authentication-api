package ar.com.auth.controllers.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {

  private String message;

  public static ErrorDTO from(String message) {
    return builder().message(message).build();
  }
}
