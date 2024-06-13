package ar.com.auth.services;

import ar.com.auth.controllers.dtos.responses.RefreshTokenResponse;
import ar.com.auth.exceptions.UserNotFoundException;
import ar.com.auth.model.Permission;
import ar.com.auth.model.SignIn;
import ar.com.auth.model.User;
import java.util.List;

public interface AuthenticationService {

  User signupUser(String userName, String userPassword, List<Permission> userPermissions)
      throws UserNotFoundException;

  SignIn signinUser(String userName, String userPassword) throws UserNotFoundException;

  RefreshTokenResponse refreshToken(User user);
}
