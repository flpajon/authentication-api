package ar.com.auth.controllers.dtos.responses;

import ar.com.auth.controllers.dtos.PermissionDTO;
import ar.com.auth.model.Permission;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SavePermissionResponse {

  private PermissionDTO permission;

  public static SavePermissionResponse from(Permission permission) {
    return builder().permission(PermissionDTO.fromForPermission(permission)).build();
  }
}
