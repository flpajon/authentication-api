package ar.com.auth.controllers.dtos;

import ar.com.auth.model.Permission;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionDTO {

  @Schema(example = "ROLE_*_WRITE or ROLE_*_READ")
  @NotBlank(message = "permissionName must not be null or empty")
  @Pattern(regexp = "^ROLE_([A-Z]{2,}_)*([A-Z][A-Z]{1,}_)*(READ|WRITE)$", message = "permissionName must be like ROLE_*_WRITE or ROLE_*_READ")
  private String permissionName;
  private String permissionDescription;
  List<UserDTO> permissionUsers;

  public static PermissionDTO fromForPermission(Permission permission) {
    return builder()
        .permissionName(permission.getPermissionName())
        .permissionDescription(permission.getPermissionDescription())
        .permissionUsers(
            permission.getPermissionUsers().stream().map(UserDTO::fromForPermission).toList())
        .build();
  }

  public static PermissionDTO fromForUser(Permission permission) {
    return builder()
        .permissionName(permission.getPermissionName())
        .permissionDescription(permission.getPermissionDescription())
        .build();
  }
}
