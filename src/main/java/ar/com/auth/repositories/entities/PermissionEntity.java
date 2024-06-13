package ar.com.auth.repositories.entities;

import ar.com.auth.model.Permission;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "permission_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionEntity extends Auditable {

  @Id
  @Column(name = "permission_name", unique = true)
  private String permissionName;
  @Column(name = "permission_description")
  private String permissionDescription;
  @ManyToMany(mappedBy = "userPermissions")
  List<UserEntity> permissionUsers;

  public static PermissionEntity fromForUser(Permission permission) {
    return PermissionEntity.builder()
        .permissionName(permission.getPermissionName())
        .permissionDescription(permission.getPermissionDescription())
        .build();
  }

  public static PermissionEntity fromForPermission(String permissionName,
      String permissionDescription) {
    return PermissionEntity.builder()
        .permissionName(permissionName)
        .permissionDescription(permissionDescription)
        .permissionUsers(List.of())
        .build();
  }
}
