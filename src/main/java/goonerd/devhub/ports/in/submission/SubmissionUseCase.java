package goonerd.devhub.ports.in.submission;

import goonerd.devhub.adapters.in.submission.command.SubmissionCommand;
import goonerd.devhub.domain.submission.Submission;

public interface SubmissionUseCase {
    Submission applySubmission(SubmissionCommand submissionApplyCommand);
    Submission approveSubmission(SubmissionCommand submissionApproveCommand);
}