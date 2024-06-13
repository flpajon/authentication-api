package ar.com.auth.services.implementations;

import ar.com.auth.controllers.dtos.responses.RefreshTokenResponse;
import ar.com.auth.exceptions.UserNotFoundException;
import ar.com.auth.model.Permission;
import ar.com.auth.model.SignIn;
import ar.com.auth.model.User;
import ar.com.auth.repositories.UserRepository;
import ar.com.auth.repositories.entities.UserEntity;
import ar.com.auth.services.AuthenticationService;
import ar.com.auth.utils.TokenGenerator;
import java.text.MessageFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImplement implements AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenGenerator tokenGenerator;

  @Autowired
  public AuthenticationServiceImplement(
      UserRepository userRepository, PasswordEncoder passwordEncoder, TokenGenerator tokenGenerator
  ) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.tokenGenerator = tokenGenerator;
  }

  @Override
  public User signupUser(String userName, String userPassword, List<Permission> userPermissions)
      throws UserNotFoundException {
    if (!userRepository.existsUserByUserName(userName)) {
      User user = new User(userName, passwordEncoder.encode(userPassword), userPermissions, true);
      return User.fromForUser(userRepository.save(UserEntity.fromForUser(user)));
    }
    throw new UserNotFoundException(
        MessageFormat.format("username {0} is already exist", userName)
    );
  }

  @Override
  public SignIn signinUser(String userName, String userPassword) throws UserNotFoundException {
    User user = User.fromForUser(userRepository.findUserByUserNameAndUserIsEnabledTrue(userName)
        .orElseThrow(() -> new UserNotFoundException(
            MessageFormat.format(
                "username {0} not found, password not match or it's disable",
                userName
            )
        )));
    if (passwordEncoder.matches(userPassword, user.getPassword())) {
      String accessToken = tokenGenerator.createAccessToken(user);
      String refreshToken = tokenGenerator.createRefreshToken(user);
      return SignIn.from(user, accessToken, refreshToken);
    }
    throw new UserNotFoundException(
        MessageFormat.format("username {0} not found, password not match or it's disable", userName)
    );
  }

  @Override
  public RefreshTokenResponse refreshToken(User user) {
    String accessToken = tokenGenerator.createAccessToken(user);
    String refreshToken = tokenGenerator.createRefreshToken(user);
    return RefreshTokenResponse.from(user, accessToken, refreshToken);
  }
}
