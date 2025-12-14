package goonerd.devhub.ports.out;

import goonerd.devhub.domain.submission.Submission;

public interface SubmissionRepository {

    Submission createSubmission(Submission submission);

    boolean existsByProjectGuidAndUserId(String projectGuid, String userId);

//    Optional<Application> findById(String applicationGuid);
//
//    boolean existsByProjectGuidAndUserId(String projectGuid, String userId);
//
//    int countByProjectGuidAndStatus(String projectGuid, ApplicationStatus status);
//
//    List<Application> findByProjectGuid(String projectGuid);
}