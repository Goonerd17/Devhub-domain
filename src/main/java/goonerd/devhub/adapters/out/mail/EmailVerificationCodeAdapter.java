package goonerd.devhub.adapters.out.mail;

import goonerd.devhub.adapters.out.mail.entity.EmailVerificationCodeEntity;
import goonerd.devhub.ports.out.mail.EmailVerificationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Transactional
public class EmailVerificationCodeAdapter implements EmailVerificationPort {

    private final EmailVerificationRepositoryJpa emailVerificationRepositoryJpa;

    @Override
    public void save(String email, String code, Duration limit) {
        LocalDateTime expiredAt = LocalDateTime.now().plus(limit);
        EmailVerificationCodeEntity entity = EmailVerificationCodeEntity.builder()
                        .email(email)
                        .code(code)
                        .expiredAt(expiredAt)
                        .build();
        emailVerificationRepositoryJpa.save(entity);
    }

    @Override
    public boolean existsValidCode(String email) {
        return emailVerificationRepositoryJpa.findById(email)
                .filter(entity -> !entity.isExpired(LocalDateTime.now()))
                .isPresent();
    }

    @Override
    public boolean verify(String email, String code) {
        EmailVerificationCodeEntity emailVerificationCodeEntity = emailVerificationRepositoryJpa.findById(email).orElse(null);
        if (emailVerificationCodeEntity == null) {
            return false;
        }
        if (emailVerificationCodeEntity.isExpired(LocalDateTime.now())) {
            return false;
        }
        if (!emailVerificationCodeEntity.getCode().equals(code)) {
            return false;
        }
        emailVerificationCodeEntity.verify(LocalDateTime.now());
        return true;
    }

    @Override
    public boolean isVerified(String email) {
        return emailVerificationRepositoryJpa.findById(email)
                .map(entity -> entity.getVerifiedAt() != null && entity.getExpiredAt().isAfter(LocalDateTime.now()))
                .orElse(false);
    }

    @Override
    public void delete(String email) {
        emailVerificationRepositoryJpa.deleteById(email);
    }
}