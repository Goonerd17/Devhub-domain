package goonerd.devhub.applications.project;

import goonerd.devhub.domains.project.Project;
import goonerd.devhub.domains.project.ProjectStatus;
import lombok.Getter;

import java.util.Objects;

@Getter
public class ProjectWithStatus {

    private final Project project;
    private final ProjectStatus projectStatus;

    public static ProjectWithStatus fromProject(Project project, ProjectStatus projectStatus) {
        return new ProjectWithStatus(project, projectStatus);
    }

    public ProjectWithStatus(Project project, ProjectStatus projectStatus) {
        this.project = Objects.requireNonNull(project);
        this.projectStatus = Objects.requireNonNull(projectStatus);
    }
}