package goonerd.devhub.adapters.out.user;

import goonerd.devhub.domain.user.UserRole;
import goonerd.devhub.domain.user.User;
import goonerd.devhub.ports.out.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    public User save(User user) {
        return UserMapper.toDomain(userRepositoryJpa.save(UserMapper.toEntity(user)));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepositoryJpa.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepositoryJpa.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    public boolean existsByRole(UserRole role) {
        return userRepositoryJpa.existsByRole(role);
    }
}