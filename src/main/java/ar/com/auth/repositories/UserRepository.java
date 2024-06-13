package ar.com.auth.repositories;

import ar.com.auth.repositories.entities.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

  Optional<UserEntity> findUserByUserNameAndUserIsEnabledTrue(String userName);

  Optional<UserEntity> findUserByUserNameAndUserIsEnabledFalse(String userName);

  Boolean existsUserByUserName(String userName);

  Optional<UserEntity> findUserByUserName(String userName);
}
