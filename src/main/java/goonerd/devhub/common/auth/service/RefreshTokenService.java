package goonerd.devhub.common.auth.service;

import goonerd.devhub.common.auth.vo.RefreshToken;
import goonerd.devhub.common.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    public void save(String userId, String token) {
        repository.save(new RefreshToken(userId, token));
    }

    public String findByUserId(String userId) {
        return repository.findById(userId)
                .map(RefreshToken::getToken)
                .orElse(null);
    }

    public void delete(String userId) {
        repository.deleteById(userId);
    }
}