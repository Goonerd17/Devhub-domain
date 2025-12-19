package goonerd.devhub.application.submission;

import goonerd.devhub.adapters.in.submission.command.SubmissionCommand;
import goonerd.devhub.common.exception.DomainRuleException;
import goonerd.devhub.domain.project.PositionSlot;
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

import java.time.LocalDate;

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

        Submission submission = Submission.createApplication(
                submissionCommand.getProjectGuid(),
                submissionCommand.getSubmitterId(),
                submissionCommand.getSubmitterName(),
                submissionCommand.getMotivation(),
                positionRequirement
        );

        return submissionRepository.createSubmission(submission);
    }

//    public void approve(String submissionGuid) {
//
//        Submission submission = submissionRepository.findBySubmissionId(submissionGuid);
//        Project project = projectRepository.findByProjectGuId(submission.getProjectGuid())
//                .orElseThrow(() -> DomainRuleException.of(ErrorCodeEnum.PROJECT_NOT_FOUND));
//
//        if (project.isClosed(LocalDate.now())) {
//            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_ALREADY_CLOSED);
//        }
//        PositionRequirement req = submission.getPositionRequirement();
//        if (!project.canApply(req.getPosition(), req.getProficiency())) {
//            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_POSITION_FULL);
//        }
//        submission.approve();
//        increaseApprovedCount(project, req);
//
//        submissionRepository.save(submission);
//        projectRepository.save(project);
//    }
//
//    private void increaseApprovedCount(Project project, PositionSlot positionSlot) {
//        project.getPositionSlots().stream()
//                .filter(p ->
//                        p.getPosition().equals(req.getPosition())
//                                && p.getProficiency().equals(req.getProficiency())
//                )
//                .findFirst()
//                .orElseThrow(() -> DomainRuleException.of(ErrorCodeEnum.PROJECT_POSITION_NOT_FOUND))
//                .increaseApprovedCount();
//    }
}
