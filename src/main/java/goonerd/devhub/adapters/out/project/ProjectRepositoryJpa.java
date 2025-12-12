package goonerd.devhub.adapters.out.project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepositoryJpa extends JpaRepository<ProjectEntity, Long> {
}
