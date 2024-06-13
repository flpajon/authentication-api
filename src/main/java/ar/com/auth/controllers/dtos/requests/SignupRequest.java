package ar.com.auth.controllers.dtos.requests;

import ar.com.auth.controllers.dtos.PermissionDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignupRequest {

  @NotBlank(message = "userName must not be null or empty")
  private String userName;
  @NotBlank(message = "userPassword must not be null or empty")
  private String userPassword;
  @NotNull(message = "userPermissions must not be null")
  @Size(min = 1, message = "userPermissions must not be empty")
  @Valid
  private List<PermissionDTO> userPermissions;
}
