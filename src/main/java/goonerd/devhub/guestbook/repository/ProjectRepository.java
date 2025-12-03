package goonerd.devhub.guestbook.repository;

import goonerd.devhub.guestbook.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
