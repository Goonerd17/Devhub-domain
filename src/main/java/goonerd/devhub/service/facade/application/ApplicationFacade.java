package goonerd.devhub.service.facade.application;

import goonerd.devhub.adapters.in.application.command.ApplyApplicationCommand;
import goonerd.devhub.adapters.in.application.dto.ApplyApplicationResponseDto;
import goonerd.devhub.domain.application.Application;
import goonerd.devhub.ports.in.ApplicationUseCase;
import goonerd.devhub.service.application.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationFacade implements ApplicationUseCase {

    private final ApplicationService applicationService;

    public ApplyApplicationResponseDto apply (ApplyApplicationCommand applyApplicationCommand) {
        Application application = applicationService.apply(applyApplicationCommand);
        return ApplyApplicationResponseDto.fromDomain(application);
    }
}