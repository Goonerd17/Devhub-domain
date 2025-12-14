package goonerd.devhub.ports.out;

import goonerd.devhub.domain.user.User;
import goonerd.devhub.domain.user.UserRole;

import java.util.Optional;

public interface UserRepository {

    User createUser(User user);
    boolean existsByUserId(String userId);// 도메인 객체를 저장
    Optional<User> findByUserId(String userId);
    boolean existsByRole(UserRole role);
}
