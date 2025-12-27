package goonerd.devhub.application.auth;

import goonerd.devhub.adapters.out.auth.entity.RefreshTokenEntity;
import goonerd.devhub.ports.out.auth.AuthPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {

    private final AuthPort authPort;

    public void save(String email, String refreshToken) {
        authPort.findByEmail(email).ifPresentOrElse(
                entity -> entity.rotate(refreshToken),
                () -> authPort.save(email, refreshToken));
    }

    public Optional<RefreshTokenEntity> findByEmail(String email) {
        return authPort.findByEmail(email);
    }

    public void delete(String email) {
        authPort.deleteByEmail(email);
    }
}