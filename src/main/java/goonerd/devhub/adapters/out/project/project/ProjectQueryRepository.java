package goonerd.devhub.adapters.out.project.project;

import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectQueryRepository {
    Page<ProjectEntity> search(SearchProjectCommand searchProjectCommand, Pageable pageable);
}