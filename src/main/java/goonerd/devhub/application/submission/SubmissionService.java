package goonerd.devhub.application.submission;

import goonerd.devhub.adapters.in.submission.command.SubmissionCommand;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;
import goonerd.devhub.domain.project.Project;
import goonerd.devhub.domain.submission.Submission;
import goonerd.devhub.ports.in.submission.SubmissionUseCase;
import goonerd.devhub.ports.out.project.ProjectRepository;
import goonerd.devhub.ports.out.submission.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SubmissionService implements SubmissionUseCase {

    private final SubmissionRepository submissionRepository;
    private final ProjectRepository projectRepository;

    public Submission applySubmission(SubmissionCommand submissionApplyCommand) {
        if (submissionRepository.existsByProjectGuidAndSubmitterId(submissionApplyCommand.getProjectGuid(), submissionApplyCommand.getSubmitterId())) {
            throw DomainRuleException.of(ErrorCodeEnum.SUBMISSION_APPLY_FAIL);
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
        projectRepository.save(project);
        return submission;
    }
}