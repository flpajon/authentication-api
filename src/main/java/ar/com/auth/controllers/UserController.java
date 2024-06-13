package ar.com.auth.controllers;

import ar.com.auth.controllers.dtos.requests.UpdateUserRequest;
import ar.com.auth.controllers.dtos.responses.DisableUserRespose;
import ar.com.auth.controllers.dtos.responses.EnableUserRespose;
import ar.com.auth.controllers.dtos.responses.FetchAllUsersResponse;
import ar.com.auth.controllers.dtos.responses.UpdateUserResponse;
import ar.com.auth.model.Permission;
import ar.com.auth.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Controller", description = "Endpoints for managing users")
@RestController
@RequestMapping(UserController.ROOT_PATH)
public class UserController {

  public static final String ROOT_PATH = "/users";

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "FetchAllUsers", description = "This method is used to return a list of users.")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("all")
  public ResponseEntity<FetchAllUsersResponse> fetchAllUsers() {
    return ResponseEntity.ok(FetchAllUsersResponse.from(userService.fetchAllUsers()));
  }

  @Operation(summary = "UpdateUserPermissions", description = "This method is used to update a user permission.")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("update")
  public ResponseEntity<UpdateUserResponse> updateUserPermissions(
      @RequestBody UpdateUserRequest updateUserRequest)
      throws Exception {
    return ResponseEntity.ok(UpdateUserResponse.from(
        userService.updateUserPermissions(updateUserRequest.getUserName(),
            updateUserRequest.getUserPermissions().stream().map(Permission::fromForUser)
                .toList())));
  }

  @Operation(summary = "SisableUser", description = "This method is used to disable a user.")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("disable")
  public ResponseEntity<DisableUserRespose> disableUser(
      @RequestParam(name = "userName") String userName)
      throws Exception {
    return ResponseEntity.ok(DisableUserRespose.from(userService.disableUser(userName)));
  }

  @Operation(summary = "EnableUser", description = "This method is used to enable a user.")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("enable")
  public ResponseEntity<EnableUserRespose> enableUser(
      @RequestParam(name = "userName") String userName)
      throws Exception {
    return ResponseEntity.ok(EnableUserRespose.from(userService.enableUser(userName)));
  }
}
