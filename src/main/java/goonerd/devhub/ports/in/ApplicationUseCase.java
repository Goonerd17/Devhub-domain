package goonerd.devhub.ports.in;

import goonerd.devhub.adapters.in.application.command.ApplyApplicationCommand;
import goonerd.devhub.adapters.in.application.dto.ApplyApplicationResponseDto;

public interface ApplicationUseCase {
    ApplyApplicationResponseDto apply (ApplyApplicationCommand applyApplicationCommand);

//    void approve(String applicationId);
//
//    void reject(String applicationId);
//
//    void cancel(String applicationId);
}
