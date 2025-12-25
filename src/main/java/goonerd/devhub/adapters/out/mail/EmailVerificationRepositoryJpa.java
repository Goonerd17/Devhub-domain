package goonerd.devhub.adapters.out.mail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationRepositoryJpa extends JpaRepository<EmailVerificationCodeEntity, String> {
}
