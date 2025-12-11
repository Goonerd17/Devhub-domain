package goonerd.devhub.common.auth.admin;

import goonerd.devhub.domain.user.User;
import goonerd.devhub.domain.user.UserRoleEnum;
import goonerd.devhub.adapters.out.user.UserEntity;
import goonerd.devhub.adapters.out.user.UserRepositoryJpa;
import goonerd.devhub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) {
        boolean existAdmin = userService.existsByRole(UserRoleEnum.ADMIN);
        if (!existAdmin) {
            userService.createAdminUser("admin@admin.co.kr", "admin", "admin1234!");
            log.info("기본 ADMIN 계정 생성됨 - ID : admin@admin.co.kr / PW : admin1234!");
        }
    }
}