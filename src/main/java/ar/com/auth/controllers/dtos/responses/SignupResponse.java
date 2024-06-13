package ar.com.auth.controllers.dtos.responses;

import ar.com.auth.controllers.dtos.UserDTO;
import ar.com.auth.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignupResponse {

  private UserDTO user;

  public static SignupResponse from(User user) {
    return builder()
        .user(UserDTO.fromForUser(user))
        .build();
  }
}
