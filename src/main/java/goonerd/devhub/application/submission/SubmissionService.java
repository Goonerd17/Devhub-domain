package goonerd.devhub.application.submission;

import goonerd.devhub.adapters.in.submission.command.SubmissionCommand;
import goonerd.devhub.domain.submission.Submission;
import goonerd.devhub.domain.submission.PositionRequirement;
import goonerd.devhub.domain.common.SkillLevel;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.ports.in.SubmissionUseCase;
import goonerd.devhub.ports.out.SubmissionRepository;
import goonerd.devhub.ports.out.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubmissionService implements SubmissionUseCase {

    private final SubmissionRepository submissionRepository;
    private final ProjectRepository projectRepository;

    public Submission createSubmission(SubmissionCommand submissionCommand) {
        SkillLevel skillLevel = SkillLevel.fromCommand(submissionCommand.getSkillLevel());
        PositionRequirement positionRequirement = PositionRequirement.fromApplyApplicationCommand(submissionCommand.getPosition(), skillLevel);

        if (submissionRepository.existsByProjectGuidAndSubmitterId(submissionCommand.getProjectGuid(), submissionCommand.getSubmitterId())) {
            throw new IllegalStateException("이미 지원한 사용자입니다.");
        }

//        Project project = projectRepository.findByProjectGuId(submissionCommand.getProjectGuid())
//                .orElseThrow(() -> new IllegalStateException("프로젝트를 찾을 수 없습니다."));
//
//        if (project.isClosed()) {
//            throw new IllegalStateException("모집 기간이 종료되었습니다.");
//        }

        Submission submission = Submission.createApplication(
                submissionCommand.getProjectGuid(),
                submissionCommand.getSubmitterId(),
                submissionCommand.getSubmitterName(),
                submissionCommand.getMotivation(),
                positionRequirement
        );

        return submissionRepository.createSubmission(submission);
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
//        project.acceptUser(app.getAuthorId());
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
