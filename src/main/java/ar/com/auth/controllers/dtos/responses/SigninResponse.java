package ar.com.auth.controllers.dtos.responses;

import ar.com.auth.controllers.dtos.UserDTO;
import ar.com.auth.model.SignIn;
import ar.com.auth.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SigninResponse {

  private UserDTO user;
  private String accessToken;
  private String refreshToken;

  public static SigninResponse from(User user, String accessToken, String refreshToken) {
    return builder()
        .user(UserDTO.fromForUser(user))
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

  public static SigninResponse from(SignIn signIn) {
    return builder()
        .user(UserDTO.fromForUser(signIn.getUser()))
        .accessToken(signIn.getAccessToken())
        .refreshToken(signIn.getRefreshToken())
        .build();
  }
}
