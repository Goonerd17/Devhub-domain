package goonerd.devhub.service.application;

import goonerd.devhub.adapters.in.application.command.ApplyApplicationCommand;
import goonerd.devhub.domain.application.Application;
import goonerd.devhub.domain.application.PositionRequirement;
import goonerd.devhub.domain.common.SkillLevel;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.ports.out.ApplicationRepository;
import goonerd.devhub.ports.out.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ProjectRepository projectRepository;

    public Application apply(ApplyApplicationCommand applyApplicationCommand) {
        SkillLevel skillLevel = SkillLevel.fromCommand(applyApplicationCommand.getSkillLevel());
        PositionRequirement requirement = PositionRequirement.fromApplyApplicationCommand(applyApplicationCommand.getPosition(), skillLevel);

        if (applicationRepository.existsByProjectGuidAndUserId(applyApplicationCommand.getProjectGuid(), applyApplicationCommand.getUserId())) {
            throw new IllegalStateException("이미 지원한 사용자입니다.");
        }

        Project project = projectRepository.findByProjectGuId(applyApplicationCommand.getProjectGuid())
                .orElseThrow(() -> new IllegalStateException("프로젝트를 찾을 수 없습니다."));

        if (project.isClosed()) {
            throw new IllegalStateException("모집 기간이 종료되었습니다.");
        }

        Application app = Application.createApplication(
                applyApplicationCommand.getProjectGuid(),
                applyApplicationCommand.getUserId(),
                applyApplicationCommand.getApplicantName(),
                applyApplicationCommand.getMotivation(),
                requirement
        );
        return applicationRepository.apply(app);
    }

//    @Override
//    public void approve(String applicationId) {
//
//        Application app = applicationRepository.findById(applicationId)
//                .orElseThrow(() -> new IllegalStateException("신청을 찾을 수 없습니다."));
//
//        Project project = projectRepository.findById(app.getProjectGuid())
//                .orElseThrow(() -> new IllegalStateException("프로젝트를 찾을 수 없습니다."));
//
//        // 정원 체크
//        int acceptedCount = applicationRepository.countAcceptedByProjectGuid(app.getProjectGuid());
//        if (acceptedCount >= project.getRecruitCount()) {
//            throw new IllegalStateException("모집 정원을 초과했습니다.");
//        }
//
//        // 지원 승인
//        app.approve();
//        applicationRepository.save(app);
//
//        // 프로젝트에 승인된 사용자 반영
//        project.acceptUser(app.getUserId());
//        projectRepository.save(project);
//    }
//
//    @Override
//    public void reject(String applicationId) {
//
//        Application app = applicationRepository.findById(applicationId)
//                .orElseThrow(() -> new IllegalStateException("신청을 찾을 수 없습니다."));
//
//        app.reject();
//        applicationRepository.save(app);
//    }
//
//    @Override
//    public void cancel(String applicationId) {
//
//        Application app = applicationRepository.findById(applicationId)
//                .orElseThrow(() -> new IllegalStateException("신청을 찾을 수 없습니다."));
//
//        app.cancel();
//        applicationRepository.save(app);
//    }
}
