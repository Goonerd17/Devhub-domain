package goonerd.devhub.adapters.out.application;

import goonerd.devhub.adapters.out.application.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepositoryJpa extends JpaRepository<ApplicationEntity, String> {
    boolean existsByProjectGuidAndApplicantGuid(String projectGuid, String submitterId);
    ApplicationEntity findByApplicationGuid(String applicationGuid);
}
