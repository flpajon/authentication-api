package ar.com.auth.services.implementations;

import ar.com.auth.exceptions.UserNotFoundException;
import ar.com.auth.model.Permission;
import ar.com.auth.model.User;
import ar.com.auth.repositories.UserRepository;
import ar.com.auth.repositories.entities.UserEntity;
import ar.com.auth.services.UserService;
import java.text.MessageFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImplement(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User fetchUserByUserName(String userName) throws UserNotFoundException {
    return User.fromForUser(
        userRepository.findUserByUserName(userName).orElseThrow(() -> new UserNotFoundException(
            MessageFormat.format(
                "username {0} not found", userName)
        )));
  }

  @Override
  public List<User> fetchAllUsers() {
    return userRepository.findAll().stream().map(User::fromForUser).toList();
  }

  @Override
  public User updateUserPermissions(String userName, List<Permission> userPermissions)
      throws UserNotFoundException {
    User user = User.fromForUser(userRepository.findUserByUserNameAndUserIsEnabledTrue(userName)
        .orElseThrow(() -> new UserNotFoundException(
            MessageFormat.format(
                "username {0} not found or it's disable", userName)
        )));
    user.setUserPermissions(userPermissions);
    return User.fromForUser(userRepository.save(UserEntity.fromForUser(user)));
  }

  @Override
  public User disableUser(String userName) throws UserNotFoundException {
    User user = User.fromForUser(userRepository.findUserByUserNameAndUserIsEnabledTrue(userName)
        .orElseThrow(() -> new UserNotFoundException(
            MessageFormat.format(
                "username {0} not found or it's disable yet",
                userName
            )
        )));
    user.setUserIsEnabled(false);
    return User.fromForUser(userRepository.save(UserEntity.fromForUser(user)));
  }

  @Override
  public User enableUser(String userName) throws UserNotFoundException {
    User user = User.fromForUser(userRepository.findUserByUserNameAndUserIsEnabledFalse(userName)
        .orElseThrow(() -> new UserNotFoundException(
            MessageFormat.format(
                "username {0} not found or it's enable yet",
                userName
            )
        )));
    user.setUserIsEnabled(true);
    return User.fromForUser(userRepository.save(UserEntity.fromForUser(user)));
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    return User.fromForUser(userRepository.findUserByUserNameAndUserIsEnabledTrue(userName)
        .orElseThrow(() -> new UsernameNotFoundException(
            MessageFormat.format(
                "username {0} not found or it's disable", userName)
        )));
  }
}
