package goonerd.devhub.adapters.out.user;

import goonerd.devhub.domain.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserId(String userId);
    boolean existsByUserId(String userId);
    boolean existsByRole(UserRole role);
}