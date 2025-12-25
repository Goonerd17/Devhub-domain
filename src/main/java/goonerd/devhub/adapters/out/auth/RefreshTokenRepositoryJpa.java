package goonerd.devhub.adapters.out.auth;

import goonerd.devhub.common.auth.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepositoryJpa extends JpaRepository<RefreshTokenEntity, String> {
    Optional<RefreshTokenEntity> findByUserId(String userId);
    void deleteByUserId(String userId);
}