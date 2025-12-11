package goonerd.devhub.adapters.out.user;

import goonerd.devhub.domain.user.UserRoleEnum;
import goonerd.devhub.domain.user.User;
import goonerd.devhub.ports.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryJpaAdapter implements UserRepository {

    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    public void save(User user) {
        userRepositoryJpa.save(UserEntity.createUserEntity(user));
    }

    @Override
    public boolean existsByUserId(String userId) {
        return userRepositoryJpa.existsByUserId(userId);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userRepositoryJpa.findByUserId(userId)
                .map(UserEntity::createUserDomain);
    }

    @Override
    public boolean existsByRole(UserRoleEnum userRole) {
        return userRepositoryJpa.existsByRole(userRole);
    }
}
