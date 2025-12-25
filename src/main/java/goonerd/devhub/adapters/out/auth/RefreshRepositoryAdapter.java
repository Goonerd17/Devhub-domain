package goonerd.devhub.adapters.out.auth;

import goonerd.devhub.ports.out.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshRepositoryAdapter implements AuthRepository {

    private final RefreshTokenRepositoryJpa refreshTokenRepositoryJpa;

    @Override
    public void save(String userId, String refreshToken) {
        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.of(userId, refreshToken);
        refreshTokenRepositoryJpa.save(refreshTokenEntity);
    }

    @Override
    public Optional<RefreshTokenEntity> findByUserId(String userId) {
        return refreshTokenRepositoryJpa.findByUserId(userId);
    }

    @Override
    public void deleteByUserId(String userId) {
        refreshTokenRepositoryJpa.deleteByUserId(userId);
    }
}