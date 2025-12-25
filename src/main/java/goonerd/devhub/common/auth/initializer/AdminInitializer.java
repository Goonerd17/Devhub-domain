package goonerd.devhub.common.auth.initializer;

import goonerd.devhub.domain.user.UserRole;
import goonerd.devhub.ports.in.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserUseCase userUseCase;

    @Override
    public void run(String... args) {
        boolean existAdmin = userUseCase.existsByRole(UserRole.ADMIN);
        if (!existAdmin) {
            userUseCase.createAdminUser("admin@admin.co.kr", "admin", "admin1234!");
            log.info("기본 ADMIN 계정 생성됨 - ID : admin@admin.co.kr / PW : admin1234!");
        }
    }
}