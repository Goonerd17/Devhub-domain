package goonerd.devhub.ports.in;

import goonerd.devhub.adapters.in.submission.command.SubmissionCommand;
import goonerd.devhub.domain.submission.Submission;

public interface SubmissionUseCase {

    Submission createSubmission(SubmissionCommand submissionCommand);
//    void approve(String applicationId);
//    void reject(String applicationId);
//    void cancel(String applicationId);
}
