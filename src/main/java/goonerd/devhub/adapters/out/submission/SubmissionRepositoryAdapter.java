package goonerd.devhub.adapters.out.submission;

import goonerd.devhub.domain.submission.Submission;
import goonerd.devhub.ports.out.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubmissionRepositoryAdapter implements SubmissionRepository {

    private final SubmissionRepositoryJpa submissionRepositoryJpa;

    @Override
    public Submission createSubmission(Submission submission) {
        SubmissionEntity submissionEntity = SubmissionMapper.toEntity(submission);
        SubmissionEntity savedSubmissionEntity = submissionRepositoryJpa.save(submissionEntity);
        return SubmissionMapper.toDomain(savedSubmissionEntity);
    }

    @Override
    public boolean existsByProjectGuidAndUserId(final String projectGuid, final String userGuid) {
        return submissionRepositoryJpa.existsByProjectGuidAndUserId(projectGuid, userGuid);
    }
}
