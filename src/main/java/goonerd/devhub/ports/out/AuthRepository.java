package goonerd.devhub.ports.out;

import goonerd.devhub.common.auth.entity.RefreshTokenEntity;

import java.util.Optional;

public interface AuthRepository {
    void save(String userId, String refreshToken);
    Optional<RefreshTokenEntity> findByUserId(String userId);
    void deleteByUserId(String userId);
}