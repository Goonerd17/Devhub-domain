package goonerd.devhub.service.user;

import goonerd.devhub.adapters.in.user.command.SignupUserCommand;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.BusinessRuleException;
import goonerd.devhub.domain.user.User;
import goonerd.devhub.domain.user.UserRole;
import goonerd.devhub.ports.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signup(SignupUserCommand signupUserCommand) {
        if (userRepository.existsByUserId(signupUserCommand.getUserId())) {
            throw BusinessRuleException.of(ErrorCodeEnum.DUPLICATE_USERID);
        }
        String encodedPassword = passwordEncoder.encode(signupUserCommand.getPassword());
        User user = User.createGeneralUser(signupUserCommand.getUserId(), signupUserCommand.getUsername(), encodedPassword);
        return userRepository.createUser(user);
    }

    public void createAdminUser(String userId, String username, String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User adminUser = User.createAdminUser(userId, username, encodedPassword);
        userRepository.createUser(adminUser);
    }

    public boolean existsByRole(UserRole role) {
        return userRepository.existsByRole(role);
    }
}