package ar.com.auth.services.implementations;

import ar.com.auth.exceptions.PermissionNotFoundException;
import ar.com.auth.model.Permission;
import ar.com.auth.repositories.PermissionRepository;
import ar.com.auth.repositories.entities.PermissionEntity;
import ar.com.auth.services.PermissionService;
import java.text.MessageFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

  private final PermissionRepository permissionRepository;

  @Autowired
  public PermissionServiceImpl(PermissionRepository permissionRepository) {
    this.permissionRepository = permissionRepository;
  }

  @Override
  public List<Permission> fetchAllPermissions() {
    return permissionRepository.findAll().stream().map(Permission::fromForPermission).toList();
  }

  @Override
  public Permission savePermission(String permissionName, String permissionDescription)
      throws PermissionNotFoundException {
    if (!permissionRepository.existsPermissionByPermissionName(permissionName)) {
      return Permission.fromForPermission(permissionRepository.save(
          PermissionEntity.fromForPermission(permissionName, permissionDescription)));
    }
    throw new PermissionNotFoundException(
        MessageFormat.format("permissionName {0} is already exist", permissionName)
    );
  }
}
