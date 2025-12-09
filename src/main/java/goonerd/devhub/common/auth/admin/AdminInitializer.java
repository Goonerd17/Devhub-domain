package goonerd.devhub.common.auth.admin;

import goonerd.devhub.common.enums.UserRoleEnum;
import goonerd.devhub.user.entity.User;
import goonerd.devhub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        boolean existAdmin = userRepository.existsByRole(UserRoleEnum.ADMIN);

        if (!existAdmin) {
            User admin = User.createAdminUser("admin", passwordEncoder.encode("admin1234!"), UserRoleEnum.ADMIN);
            userRepository.save(admin);
            log.info("기본 ADMIN 계정 생성됨 - ID : admin / PW : admin1234!");
        }
    }
}