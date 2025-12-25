package goonerd.devhub.adapters.out.project;

import goonerd.devhub.adapters.out.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepositoryJpa extends JpaRepository<ProjectEntity, Long> {
    Optional<ProjectEntity> findByProjectGuid(String projectGuid);
}
