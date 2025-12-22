package goonerd.devhub.application.submission;

import goonerd.devhub.adapters.in.submission.command.SubmissionCommand;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.domain.submission.Submission;
import goonerd.devhub.ports.in.SubmissionUseCase;
import goonerd.devhub.ports.out.ProjectRepository;
import goonerd.devhub.ports.out.SubmissionRepository;
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

    public Submission applySubmission(SubmissionCommand submissionApplyCommand) {
        if (submissionRepository.existsByProjectGuidAndSubmitterId(submissionApplyCommand.getProjectGuid(), submissionApplyCommand.getSubmitterId())) {
            throw new IllegalStateException("이미 지원한 사용자입니다.");
        }

        Submission submission = Submission.createApplication(
                submissionApplyCommand.getProjectGuid(),
                submissionApplyCommand.getSubmitterId(),
                submissionApplyCommand.getSubmitterName(),
                submissionApplyCommand.getMotivation(),
                submissionApplyCommand.getPosition(),
                submissionApplyCommand.getSkillLevel()
        );

        return submissionRepository.save(submission);
    }

    public Submission approveSubmission(SubmissionCommand submissionApproveCommand) {
        Submission submission = submissionRepository.findBySubmissionGuid(submissionApproveCommand.getSubmissionGuid());
        Project project = projectRepository.findByProjectGuId(submission.getProjectGuid())
                .orElseThrow(() -> DomainRuleException.of(ErrorCodeEnum.SUBMISSION_PROJECT_FAIL));

        project.approveSubmission(submission);
        submissionRepository.save(submission);
        return submission;
    }
}