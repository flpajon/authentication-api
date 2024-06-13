package ar.com.auth.model;

import ar.com.auth.controllers.dtos.PermissionDTO;
import ar.com.auth.repositories.entities.PermissionEntity;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Permission {

  private String permissionName;
  private String permissionDescription;
  List<User> permissionUsers;

  public static Permission fromForUser(PermissionEntity permissionEntity) {
    return Permission.builder()
        .permissionName(permissionEntity.getPermissionName())
        .permissionDescription(permissionEntity.getPermissionDescription())
        .build();
  }

  public static Permission fromForUser(PermissionDTO permissionDto) {
    return Permission.builder()
        .permissionName(permissionDto.getPermissionName())
        .permissionDescription(permissionDto.getPermissionDescription())
        .build();
  }

  public static Permission fromForPermission(PermissionEntity permissionEntity) {
    return Permission.builder()
        .permissionName(permissionEntity.getPermissionName())
        .permissionDescription(permissionEntity.getPermissionDescription())
        .permissionUsers(
            permissionEntity.getPermissionUsers().stream().map(User::fromForPermission).toList())
        .build();
  }
}
