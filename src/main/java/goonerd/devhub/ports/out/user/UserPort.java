package goonerd.devhub.ports.out.user;

import goonerd.devhub.domains.user.User;
import goonerd.devhub.domains.user.UserRole;

import java.util.Optional;

public interface UserPort {

    User save(User user);
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    boolean existsByRole(UserRole role);
}
