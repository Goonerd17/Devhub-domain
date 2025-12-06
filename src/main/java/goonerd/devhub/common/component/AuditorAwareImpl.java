package goonerd.devhub.common.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorProvider")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // SecurityContextHolder.getContext().getAuthentication().getName()
        return Optional.of("SYSTEM"); // 테스트용 기본값
    }
}
