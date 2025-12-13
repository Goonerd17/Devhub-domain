package goonerd.devhub.adapters.out.user;

import goonerd.devhub.domain.user.UserRole;
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
    public User createUser(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        UserEntity createdUserEntity = userRepositoryJpa.save(userEntity);
        return UserMapper.toDomain(createdUserEntity);
    }

    @Override
    public boolean existsByUserId(String userId) {
        return userRepositoryJpa.existsByUserId(userId);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userRepositoryJpa.findByUserId(userId)
                .map(UserMapper::toDomain);
    }

    @Override
    public boolean existsByRole(UserRole role) {
        return userRepositoryJpa.existsByRole(role);
    }
}