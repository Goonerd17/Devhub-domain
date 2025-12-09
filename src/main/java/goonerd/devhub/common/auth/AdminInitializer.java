package goonerd.devhub.common.auth;

import goonerd.devhub.common.enums.UserRoleEnum;
import goonerd.devhub.user.entity.User;
import goonerd.devhub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        boolean existAdmin = userRepository.existsByRole(UserRoleEnum.ADMIN);

        if (!existAdmin) {
            User admin = new User("admin", passwordEncoder.encode("admin1234!"), UserRoleEnum.ADMIN);
            userRepository.save(admin);
            log.info(">>> 기본 ADMIN 계정 생성됨 : admin/admin1234!");
        }
    }
}
