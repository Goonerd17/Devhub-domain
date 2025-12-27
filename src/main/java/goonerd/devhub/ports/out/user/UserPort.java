package goonerd.devhub.ports.out.user;

import goonerd.devhub.domain.user.User;
import goonerd.devhub.domain.user.UserRole;

import java.util.Optional;

public interface UserPort {

    User save(User user);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    boolean existsByRole(UserRole role);
}
