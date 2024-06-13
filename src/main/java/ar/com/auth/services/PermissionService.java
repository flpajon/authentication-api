package ar.com.auth.services;

import ar.com.auth.exceptions.PermissionNotFoundException;
import ar.com.auth.model.Permission;
import java.util.List;

public interface PermissionService {

  List<Permission> fetchAllPermissions();

  Permission savePermission(String permissionName, String permissionDescription)
      throws PermissionNotFoundException;
}
