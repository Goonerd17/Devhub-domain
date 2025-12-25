package goonerd.devhub.adapters.out.mail;

import goonerd.devhub.adapters.out.mail.entity.EmailVerificationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationRepositoryJpa extends JpaRepository<EmailVerificationCodeEntity, String> {
}
