package ar.com.auth.controllers;

import ar.com.auth.controllers.dtos.UserDTO;
import ar.com.auth.controllers.dtos.requests.SigninRequest;
import ar.com.auth.controllers.dtos.requests.SignupRequest;
import ar.com.auth.controllers.dtos.responses.RefreshTokenResponse;
import ar.com.auth.controllers.dtos.responses.SigninResponse;
import ar.com.auth.model.Permission;
import ar.com.auth.model.User;
import ar.com.auth.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Controller", description = "Endpoints for managing authentications")
@RestController
@RequestMapping(AuthenticationController.ROOT_PATH)
public class AuthenticationController {

  public static final String ROOT_PATH = "/authentication";
  private final AuthenticationService authenticationService;

  @Autowired
  public AuthenticationController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @Operation(summary = "SignUp", description = "This method is used to signup a new user.")
  @CrossOrigin(origins = "*")
  @PostMapping("signup")
  public ResponseEntity<UserDTO> signUp(@Valid @RequestBody SignupRequest signupRequest)
      throws Exception {
    return ResponseEntity.ok(UserDTO.fromForUser(
        authenticationService.signupUser(signupRequest.getUserName().toUpperCase(),
            signupRequest.getUserPassword(),
            signupRequest.getUserPermissions().stream().map(Permission::fromForUser).toList()
        )));
  }

  @Operation(summary = "SignIn", description = "This method is used to signin a exist user.")
  @CrossOrigin(origins = "*")
  @PostMapping("signin")
  public ResponseEntity<SigninResponse> signIn(@Valid @RequestBody SigninRequest signinRequest)
      throws Exception {
    return ResponseEntity.ok(SigninResponse.from(
        authenticationService.signinUser(signinRequest.getUserName().toUpperCase(),
            signinRequest.getUserPassword())));
  }


  @Operation(summary = "RefreshToken", description = "This method is used to refresh the access token.")
  @CrossOrigin(origins = "*")
  @GetMapping("refreshToken")
  public ResponseEntity<RefreshTokenResponse> refreshToken(@AuthenticationPrincipal User user) {
    return ResponseEntity.ok(authenticationService.refreshToken(user));
  }
}
