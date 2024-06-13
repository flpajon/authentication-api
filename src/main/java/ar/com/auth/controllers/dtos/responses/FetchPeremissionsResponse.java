package ar.com.auth.controllers.dtos.responses;

import ar.com.auth.controllers.dtos.PermissionDTO;
import ar.com.auth.model.Permission;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FetchPeremissionsResponse {

  private List<PermissionDTO> permissionsList;

  public static FetchPeremissionsResponse from(List<Permission> permissions) {
    return builder()
        .permissionsList(permissions.stream().map(PermissionDTO::fromForPermission).toList())
        .build();
  }
}
