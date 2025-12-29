package goonerd.devhub.ports.out.user;

import goonerd.devhub.domains.user.User;
import goonerd.devhub.domains.user.UserRole;

import java.util.Optional;

public interface UserPort {

    User save(User user);
    Optional<User> findByEmail(String email);
    boolean existsByRole(UserRole role);
    boolean isSameUser(String userGuid, String otherUserGuid);
    boolean existsByEmail(String email);
}
