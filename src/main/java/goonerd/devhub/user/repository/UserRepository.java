package goonerd.devhub.user.repository;

import goonerd.devhub.common.enums.UserRoleEnum;
import goonerd.devhub.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
    boolean existsByRole(UserRoleEnum role);
}