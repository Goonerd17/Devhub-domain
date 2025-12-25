package goonerd.devhub.ports.out;

import goonerd.devhub.adapters.out.auth.RefreshTokenEntity;

import java.util.Optional;

public interface AuthRepository {
    void save(String userId, String refreshToken);
    Optional<RefreshTokenEntity> findByUserId(String userId);
    void deleteByUserId(String userId);
}