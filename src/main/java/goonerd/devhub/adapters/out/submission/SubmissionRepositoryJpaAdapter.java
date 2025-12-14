package goonerd.devhub.adapters.out.submission;

import goonerd.devhub.domain.submission.Submission;
import goonerd.devhub.ports.out.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubmissionRepositoryJpaAdapter implements SubmissionRepository {

    private final SubmissionRepositoryJpa submissionRepositoryJpa;

    @Override
    public Submission apply(Submission submission) {
        SubmissionEntity submissionEntity = SubmissionMapper.toEntity(submission);
        SubmissionEntity appliedSubmissionEntity = submissionRepositoryJpa.save(submissionEntity);
        return SubmissionMapper.toDomain(appliedSubmissionEntity);
    }

    @Override
    public boolean existsByProjectGuidAndUserId(final String projectGuid, final String userGuid) {
        return submissionRepositoryJpa.existsByProjectGuidAndUserId(projectGuid, userGuid);
    }
}
