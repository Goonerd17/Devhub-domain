package goonerd.devhub.ports.in.application;

import goonerd.devhub.adapters.in.application.command.ApplicationCommand;
import goonerd.devhub.domain.application.Application;

public interface ApplicationUseCase {
    Application applyApplication(ApplicationCommand applyApplicationCommand);
    Application approveApplication(ApplicationCommand approveApplicationCommand);
}