package goonerd.devhub.adapters.in.facade.submission;

import goonerd.devhub.adapters.in.submission.command.SubmissionCommand;
import goonerd.devhub.adapters.in.submission.dto.SubmissionResponseDto;
import goonerd.devhub.domain.submission.Submission;
import goonerd.devhub.ports.in.SubmissionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionFacade {

    private final SubmissionUseCase submissionUseCase;

    public SubmissionResponseDto apply (SubmissionCommand submissionCommand) {
        return SubmissionResponseDto.fromDomain(submissionUseCase.apply(submissionCommand));
    }
}