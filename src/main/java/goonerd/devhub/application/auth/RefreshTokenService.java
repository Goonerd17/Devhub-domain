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

    public void save(String email, String refreshToken) {
        authRepository.findByEmail(email).ifPresentOrElse(
                entity -> entity.rotate(refreshToken),
                () -> authRepository.save(email, refreshToken));
    }

    public Optional<RefreshTokenEntity> findByEmail(String email) {
        return authRepository.findByEmail(email);
    }

    public void delete(String email) {
        authRepository.deleteByEmail(email);
    }
}