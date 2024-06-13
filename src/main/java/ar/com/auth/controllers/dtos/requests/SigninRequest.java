package ar.com.auth.controllers.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SigninRequest {

  @NotBlank(message = "userName must not be null or empty")
  private String userName;
  @NotBlank(message = "userPassword must not be null or empty")
  private String userPassword;
}
