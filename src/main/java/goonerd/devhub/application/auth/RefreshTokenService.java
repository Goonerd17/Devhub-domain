package goonerd.devhub.application.auth;

import goonerd.devhub.adapters.out.auth.entity.RefreshTokenEntity;
import goonerd.devhub.ports.out.auth.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final AuthRepository authRepository;

    public void save(String userId, String refreshToken) {
        authRepository.findByUserId(userId).ifPresentOrElse(
                entity -> entity.rotate(refreshToken),
                () -> authRepository.save(userId, refreshToken));
    }

    public Optional<RefreshTokenEntity> findByUserId(String userId) {
        return authRepository.findByUserId(userId);
    }

    public void delete(String userId) {
        authRepository.deleteByUserId(userId);
    }
}