package ar.com.auth.controllers;

import ar.com.auth.controllers.dtos.requests.SavePermissionRequest;
import ar.com.auth.controllers.dtos.responses.FetchPeremissionsResponse;
import ar.com.auth.controllers.dtos.responses.SavePermissionResponse;
import ar.com.auth.services.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Permission Controller", description = "Endpoints for managing permissions")
@RestController
@RequestMapping(PermissionController.ROOT_PATH)
public class PermissionController {

  public static final String ROOT_PATH = "/permissions";

  private final PermissionService permissionService;

  @Autowired
  public PermissionController(PermissionService permissionService) {
    this.permissionService = permissionService;
  }

  @Operation(summary = "FetchAllPermissions", description = "This method is used to return a list of permissions.")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("all")
  public ResponseEntity<FetchPeremissionsResponse> fetchAllPermissions() {
    return ResponseEntity.ok(
        FetchPeremissionsResponse.from(permissionService.fetchAllPermissions()));
  }

  @Operation(summary = "SavePermissions", description = "This method is used to save a permission.")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("save")
  public ResponseEntity<SavePermissionResponse> savePermissions(
      @Valid @RequestBody SavePermissionRequest request) throws Exception {
    return ResponseEntity.ok(SavePermissionResponse.from(
        permissionService.savePermission(request.getPermissionName(),
            request.getPermissionDescription())));
  }
}
