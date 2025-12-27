package goonerd.devhub.application.application;

import goonerd.devhub.adapters.in.application.command.ApplicationCommand;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;
import goonerd.devhub.domain.application.Application;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.ports.in.application.ApplicationUseCase;
import goonerd.devhub.ports.out.common.IdentifierGeneratorPort;
import goonerd.devhub.ports.out.project.ProjectRepository;
import goonerd.devhub.ports.out.application.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationService implements ApplicationUseCase {

    private final ApplicationRepository applicationRepository;
    private final ProjectRepository projectRepository;
    private final IdentifierGeneratorPort identifierGeneratorPort;

    public Application applyApplication(ApplicationCommand applyApplicationCommand) {
        if (applicationRepository.existsByProjectGuidAndApplicantGuid(applyApplicationCommand.getProjectGuid(), applyApplicationCommand.getApplicantGuid())) {
            throw DomainRuleException.of(ErrorCodeEnum.SUBMISSION_APPLY_FAIL);
        }

        String applicationGuid = identifierGeneratorPort.generate();
        Application application = Application.createApplication(
                applicationGuid,
                applyApplicationCommand.getProjectGuid(),
                applyApplicationCommand.getApplicationGuid(),
                applyApplicationCommand.getApplicantEmail(),
                applyApplicationCommand.getApplicantName(),
                applyApplicationCommand.getMotivation(),
                applyApplicationCommand.getPositionName(),
                applyApplicationCommand.getSkillLevel()
        );

        return applicationRepository.save(application);
    }

    public Application approveApplication(ApplicationCommand approveApplicationCommand) {
        Application application = applicationRepository.findByApplicationGuid(approveApplicationCommand.getApplicantGuid());
        Project project = projectRepository.findByProjectGuId(application.getProjectGuid())
                .orElseThrow(() -> DomainRuleException.of(ErrorCodeEnum.SUBMISSION_PROJECT_FAIL));

        project.approveApplication(application);
        applicationRepository.save(application);
        projectRepository.save(project);
        return application;
    }
}