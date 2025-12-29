package goonerd.devhub.adapters.out.application;

import goonerd.devhub.adapters.out.application.entity.ApplicationEntity;
import goonerd.devhub.domains.application.Application;
import goonerd.devhub.ports.out.application.ApplicationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationAdapter implements ApplicationPort {

    private final ApplicationRepositoryJpa applicationRepositoryJpa;

    @Override
    public Application save(Application application) {
        ApplicationEntity applicationEntity = ApplicationMapper.toEntity(application);
        ApplicationEntity savedApplicationEntity = applicationRepositoryJpa.save(applicationEntity);
        return ApplicationMapper.toDomain(savedApplicationEntity);
    }

    @Override
    public boolean existsByProjectGuidAndApplicantGuid(final String projectGuid, final String applicantGuid) {
        return applicationRepositoryJpa.existsByProjectGuidAndApplicantGuid(projectGuid, applicantGuid);
    }

    @Override
    public Application findByApplicationGuid(String applicantGuid) {
        return ApplicationMapper.toDomain(applicationRepositoryJpa.findByApplicationGuid(applicantGuid));
    }
}