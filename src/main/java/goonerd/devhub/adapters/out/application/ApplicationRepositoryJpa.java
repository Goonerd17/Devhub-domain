package goonerd.devhub.adapters.out.application;

import goonerd.devhub.adapters.out.project.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepositoryJpa extends JpaRepository<ApplicationEntity, String> {
    boolean existsByProjectGuidAndUserId(String projectGuid, String userId);
}
