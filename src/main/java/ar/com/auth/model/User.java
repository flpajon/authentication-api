package ar.com.auth.model;

import ar.com.auth.repositories.entities.UserEntity;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class User implements UserDetails {

  private String userName;
  private String userPassword;
  private List<Permission> userPermissions;
  private Boolean userIsEnabled;

  public static User fromForUser(UserEntity userEntity) {
    return User.builder()
        .userName(userEntity.getUserName())
        .userPassword(userEntity.getUserPassword())
        .userPermissions(
            userEntity.getUserPermissions().stream().map(Permission::fromForUser).toList())
        .userIsEnabled(userEntity.getUserIsEnabled())
        .build();
  }

  public static User fromForPermission(UserEntity userEntity) {
    return User.builder()
        .userName(userEntity.getUserName())
        .userPassword(userEntity.getUserPassword())
        .userIsEnabled(userEntity.getUserIsEnabled())
        .build();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return userPermissions.stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return userPassword;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return getUserIsEnabled();
  }
}
