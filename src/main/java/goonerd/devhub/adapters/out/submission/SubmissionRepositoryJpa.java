package goonerd.devhub.adapters.out.submission;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepositoryJpa extends JpaRepository<SubmissionEntity, String> {
    boolean existsByProjectGuidAndSubmitterId(String projectGuid, String submitterId);
}
