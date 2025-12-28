package goonerd.devhub.applications.project;

import goonerd.devhub.adapters.in.project.command.CreateProjectCommand;
import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import goonerd.devhub.adapters.in.common.pagination.PageCommand;
import goonerd.devhub.domains.position.Position;
import goonerd.devhub.domains.project.Project;
import goonerd.devhub.ports.in.project.ProjectUseCase;
import goonerd.devhub.ports.out.common.IdentifierGeneratorPort;
import goonerd.devhub.ports.out.project.ProjectPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService implements ProjectUseCase {

    private final ProjectPort projectPort;
    private final IdentifierGeneratorPort identifierGeneratorPort;

    public Page<ProjectWithStatus> listProject(SearchProjectCommand searchProjectCommand, PageCommand pageCommand) {
        LocalDate currentLocalDate = LocalDate.now();
        Page<Project> pagedProjectList = projectPort.listProject(searchProjectCommand, pageCommand);
        List<ProjectWithStatus> pagedProjectWithStatusList = pagedProjectList.getContent()
                .stream()
                .map(project -> ProjectWithStatus.fromProject(project, project.calculateStatus(currentLocalDate)))
                .toList();
        return new PageImpl<>(pagedProjectWithStatusList, pagedProjectList.getPageable(), pagedProjectList.getTotalElements());
    }

    public Project createProject(CreateProjectCommand createProjectCommand) {

        String projectGuid = identifierGeneratorPort.generate();

        List<Position> positionList = createProjectCommand.getPositionList().stream()
                        .map(positionCommand -> Position.createPosition(
                                identifierGeneratorPort.generate(),
                                projectGuid,
                                positionCommand.getPositionName(),
                                positionCommand.getCapacity(),
                                positionCommand.getLevel(),
                                0))
                        .toList();

        Project project = Project.createNew(
                projectGuid,
                createProjectCommand.getAuthorGuid(),
                createProjectCommand.getAuthorName(),
                createProjectCommand.getTitle(),
                createProjectCommand.getContent(),
                createProjectCommand.getRecruitmentType(),
                createProjectCommand.getProgressType(),
                createProjectCommand.getStartDate(),
                createProjectCommand.getEndDate(),
                positionList,
                createProjectCommand.getSkillList(),
                createProjectCommand.getRecruitCount()
        );
        return projectPort.save(project);
    }
}