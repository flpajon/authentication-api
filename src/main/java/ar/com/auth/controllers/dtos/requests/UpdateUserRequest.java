package ar.com.auth.controllers.dtos.requests;

import ar.com.auth.controllers.dtos.PermissionDTO;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateUserRequest {

  private String userName;
  private List<PermissionDTO> userPermissions;
}
