package ar.com.auth.controllers.dtos.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SavePermissionRequest {

  @Schema(example = "ROLE_*_WRITE or ROLE_*_READ")
  @NotBlank(message = "permissionName must not be null or empty")
  @Pattern(regexp = "^ROLE_([A-Z]{2,}_)*([A-Z][A-Z]{1,}_)*(READ|WRITE)$", message = "permissionName must be like ROLE_*_WRITE or ROLE_*_READ")
  private String permissionName;
  @NotBlank(message = "permissionDescription must not be null or empty")
  private String permissionDescription;
}
