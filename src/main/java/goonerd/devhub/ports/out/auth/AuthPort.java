package goonerd.devhub.ports.out.auth;

import goonerd.devhub.adapters.out.auth.entity.RefreshTokenEntity;

import java.util.Optional;

public interface AuthPort {
    void save(String email, String refreshToken);
    Optional<RefreshTokenEntity> findByEmail(String email);
    void deleteByEmail(String email);
}