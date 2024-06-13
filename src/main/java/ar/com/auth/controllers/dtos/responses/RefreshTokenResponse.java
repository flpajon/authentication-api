package ar.com.auth.controllers.dtos.responses;

import ar.com.auth.controllers.dtos.UserDTO;
import ar.com.auth.model.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RefreshTokenResponse {

  private UserDTO user;
  private String accessToken;
  private String refreshToken;

  public static RefreshTokenResponse from(User user, String accessToken, String refreshToken) {
    return builder()
        .user(UserDTO.fromForUser(user))
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }
}
