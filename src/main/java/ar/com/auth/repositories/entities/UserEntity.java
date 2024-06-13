package ar.com.auth.repositories.entities;

import ar.com.auth.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity extends AuditableBasic {

  @Id
  @Column(name = "user_name")
  private String userName;
  @Column(name = "user_password")
  private String userPassword;
  @ManyToMany
  @JoinTable(
      name = "user_permissions",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "permission_name"))
  private List<PermissionEntity> userPermissions;
  @Column(name = "user_is_enabled")
  private Boolean userIsEnabled;

  public static UserEntity fromForUser(User user) {
    return UserEntity.builder()
        .userName(user.getUsername())
        .userPassword(user.getUserPassword())
        .userPermissions(
            user.getUserPermissions().stream().map(PermissionEntity::fromForUser).toList())
        .userIsEnabled(user.getUserIsEnabled())
        .build();
  }
}
