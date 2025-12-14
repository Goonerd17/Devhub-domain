package goonerd.devhub.service.project;

import goonerd.devhub.adapters.in.project.command.CreateProjectCommand;
import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import goonerd.devhub.common.vo.PageCommand;
import goonerd.devhub.domain.project.PositionSlot;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.domain.project.ProjectProgressType;
import goonerd.devhub.domain.project.RecruitmentType;
import goonerd.devhub.ports.out.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Page<Project> listProject(SearchProjectCommand searchProjectCommand, PageCommand pageCommand) {
        return projectRepository.listProject(pageCommand);
    }

    public Project createProject(CreateProjectCommand createProjectCommand) {

        RecruitmentType recruitmentType = RecruitmentType.from(createProjectCommand.getRecruitmentType());
        ProjectProgressType progressType = ProjectProgressType.from(createProjectCommand.getProjectProgressType());
        List<PositionSlot> positionSlots = createProjectCommand.getPositions().stream()
                        .map(p -> new PositionSlot(
                                p.getPosition(),
                                p.getProficiency(),
                                p.getCapacity(),
                                List.of()))
                        .toList();

        Project project = Project.createNew(
                createProjectCommand.getUserId(),
                createProjectCommand.getUsername(),
                createProjectCommand.getTitle(),
                createProjectCommand.getContent(),
                createProjectCommand.getRecruitCount(),
                recruitmentType,
                progressType,
                createProjectCommand.getStartDate(),
                createProjectCommand.getEndDate(),
                positionSlots,
                createProjectCommand.getSkills()
        );

        return projectRepository.createProject(project);
    }
}