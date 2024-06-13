package ar.com.auth.controllers.dtos;

import ar.com.auth.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

  private String userName;
  private List<PermissionDTO> userPermissions;
  private Boolean userIsEnabled;

  public static UserDTO fromForUser(User user) {
    if (user != null) {
      return builder()
          .userName(user.getUsername())
          .userPermissions(
              user.getUserPermissions().stream().map(PermissionDTO::fromForUser).toList())
          .userIsEnabled(user.getUserIsEnabled())
          .build();
    }
    return null;
  }

  public static UserDTO fromForPermission(User user) {
    if (user != null) {
      return builder()
          .userName(user.getUsername())
          .userIsEnabled(user.getUserIsEnabled())
          .build();
    }
    return null;
  }
}
