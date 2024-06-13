package ar.com.auth.services;

import ar.com.auth.exceptions.UserNotFoundException;
import ar.com.auth.model.Permission;
import ar.com.auth.model.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  User fetchUserByUserName(String userName) throws UserNotFoundException;

  List<User> fetchAllUsers();

  User updateUserPermissions(String userName, List<Permission> userPermissions)
      throws UserNotFoundException;

  User disableUser(String userName) throws UserNotFoundException;

  User enableUser(String userName) throws UserNotFoundException;
}
