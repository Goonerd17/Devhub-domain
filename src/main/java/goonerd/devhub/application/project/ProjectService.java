package goonerd.devhub.application.project;

import goonerd.devhub.adapters.in.project.command.CreateProjectCommand;
import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import goonerd.devhub.adapters.in.vo.PageCommand;
import goonerd.devhub.domain.project.PositionSlot;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.domain.project.ProjectProgressType;
import goonerd.devhub.domain.project.RecruitmentType;
import goonerd.devhub.ports.in.ProjectUseCase;
import goonerd.devhub.ports.out.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService implements ProjectUseCase {

    private final ProjectRepository projectRepository;

    public Page<ProjectWithStatus> listProject(SearchProjectCommand searchProjectCommand, PageCommand pageCommand) {
        LocalDate currentLocalDate = LocalDate.now();
        Page<Project> pagedProjectList = projectRepository.listProject(searchProjectCommand, pageCommand);
        List<ProjectWithStatus> pagedProjectWithStatusList = pagedProjectList.getContent()
                .stream()
                .map(project -> ProjectWithStatus.fromProject(project, project.calculateStatus(currentLocalDate)))
                .toList();
        return new PageImpl<>(pagedProjectWithStatusList, pagedProjectList.getPageable(), pagedProjectList.getTotalElements());
    }

    public Project createProject(CreateProjectCommand createProjectCommand) {

        List<PositionSlot> positionSlotList = createProjectCommand.getPositions().stream()
                        .map(p -> PositionSlot.createPositionSlot(
                                p.getPosition(),
                                p.getProficiency(),
                                p.getCapacity()))
                        .toList();

        Project project = Project.createNew(
                createProjectCommand.getAuthorId(),
                createProjectCommand.getAuthorName(),
                createProjectCommand.getTitle(),
                createProjectCommand.getDescription(),
                createProjectCommand.getRecruitCount(),
                createProjectCommand.getRecruitmentType(),
                createProjectCommand.getProjectProgressType(),
                createProjectCommand.getStartDate(),
                createProjectCommand.getEndDate(),
                positionSlotList,
                createProjectCommand.getSkills()
        );

        return projectRepository.createProject(project);
    }
}