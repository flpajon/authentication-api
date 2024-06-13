package ar.com.auth.repositories;

import ar.com.auth.model.Permission;
import ar.com.auth.repositories.entities.PermissionEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, String> {

  Optional<Permission> findPermissionByPermissionName(String permissionName);

  Boolean existsPermissionByPermissionName(String permissionName);
}
