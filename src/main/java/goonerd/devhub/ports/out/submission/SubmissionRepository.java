package goonerd.devhub.ports.out.submission;

import goonerd.devhub.domain.submission.Submission;

public interface SubmissionRepository {
    Submission save(Submission submission);
    boolean existsByProjectGuidAndSubmitterId(String projectGuid, String submitterId);
    Submission findBySubmissionGuid(String submissionGuid);
}