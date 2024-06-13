package ar.com.auth.controllers.dtos.responses;

import ar.com.auth.controllers.dtos.UserDTO;
import ar.com.auth.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchAllUsersResponse {

  private List<UserDTO> usersList;

  public static FetchAllUsersResponse from(List<User> users) {
    return builder()
        .usersList(users.stream().map(UserDTO::fromForUser).toList())
        .build();
  }
}
