package goonerd.devhub.adapters.out.user;

import goonerd.devhub.domains.user.UserRole;
import goonerd.devhub.domains.user.User;
import goonerd.devhub.ports.out.user.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserPort {

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

    @Override
    public boolean isSameUser(String userGuid, String otherUserGuid) {
        return userGuid != null && userGuid.equals(otherUserGuid);
    }
}