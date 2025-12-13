package goonerd.devhub.adapters.out.application;

import goonerd.devhub.domain.application.Application;
import goonerd.devhub.ports.out.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationRepositoryJpaAdapter implements ApplicationRepository {

    private final ApplicationRepositoryJpa applicationRepositoryJpa;

    @Override
    public Application applyApplication(Application application) {
        ApplicationEntity applicationEntity = ApplicationMapper.toEntity(application);
        ApplicationEntity appliedApplicationEntity = applicationRepositoryJpa.save(applicationEntity);
        return ApplicationMapper.toDomain(appliedApplicationEntity);
    }

    @Override
    public boolean existsByProjectGuidAndUserId(final String projectGuid, final String userGuid) {
        return applicationRepositoryJpa.existsByProjectGuidAndUserId(projectGuid, userGuid);
    }
}
