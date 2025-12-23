package goonerd.devhub.common.auth.service;

import goonerd.devhub.common.auth.entity.RefreshTokenEntity;
import goonerd.devhub.common.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void save(String userId, String refreshToken) {
        refreshTokenRepository.findByUserId(userId)
                .ifPresentOrElse(
                        entity -> entity.rotate(refreshToken),
                        () -> refreshTokenRepository.save(
                                RefreshTokenEntity.of(userId, refreshToken)
                        )
                );
    }

    public RefreshTokenEntity findByUserId(String userId) {
        return refreshTokenRepository.findByUserId(userId).orElse(null);
    }

    public void delete(String userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }
}