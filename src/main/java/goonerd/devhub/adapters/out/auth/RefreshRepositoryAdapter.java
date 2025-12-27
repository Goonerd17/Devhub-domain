package goonerd.devhub.adapters.out.auth;

import goonerd.devhub.adapters.out.auth.entity.RefreshTokenEntity;
import goonerd.devhub.ports.out.auth.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefreshRepositoryAdapter implements AuthRepository {

    private final RefreshTokenRepositoryJpa refreshTokenRepositoryJpa;

    @Override
    public void save(String email, String refreshToken) {
        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.of(email, refreshToken);
        refreshTokenRepositoryJpa.save(refreshTokenEntity);
    }

    @Override
    public Optional<RefreshTokenEntity> findByEmail(String email) {
        return refreshTokenRepositoryJpa.findByEmail(email);
    }

    @Override
    public void deleteByEmail(String email) {
        refreshTokenRepositoryJpa.deleteByEmail(email);
    }
}