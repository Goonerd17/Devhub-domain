package goonerd.devhub.adapters.out.mail;

import goonerd.devhub.adapters.out.mail.entity.EmailCertificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailCertificationRepositoryJpa extends JpaRepository<EmailCertificationEntity, String> {
}
