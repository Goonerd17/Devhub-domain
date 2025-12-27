package goonerd.devhub.adapters.out.user;

import goonerd.devhub.adapters.out.user.entity.UserEntity;
import goonerd.devhub.domain.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByRole(UserRole role);
}