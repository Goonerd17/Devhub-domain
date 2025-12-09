package goonerd.devhub.common.auth.service;

import goonerd.devhub.common.auth.vo.RefreshToken;
import goonerd.devhub.common.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    public void save(String username, String token) {
        repository.save(new RefreshToken(username, token));
    }

    public String findByUsername(String username) {
        return repository.findById(username)
                .map(RefreshToken::getToken)
                .orElse(null);
    }

    public void delete(String username) {
        repository.deleteById(username);
    }
}