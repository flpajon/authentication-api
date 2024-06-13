package ar.com.auth.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignIn {

  private User user;
  private String accessToken;
  private String refreshToken;

  public static SignIn from(User user, String accessToken, String refreshToken) {
    return builder()
        .user(user)
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }
}
