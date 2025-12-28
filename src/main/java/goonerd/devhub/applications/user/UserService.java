package goonerd.devhub.applications.user;

import goonerd.devhub.adapters.in.user.command.SignupUserCommand;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.BusinessRuleException;
import goonerd.devhub.domains.user.User;
import goonerd.devhub.domains.user.UserRole;
import goonerd.devhub.ports.in.user.UserUseCase;
import goonerd.devhub.ports.out.common.IdentifierGeneratorPort;
import goonerd.devhub.ports.out.common.PasswordPolicyPort;
import goonerd.devhub.ports.out.mail.EmailVerificationPort;
import goonerd.devhub.ports.out.user.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserUseCase {

    private final UserPort userPort;
    private final EmailVerificationPort emailVerificationPort;
    private final PasswordPolicyPort passwordPolicyPort;
    private final IdentifierGeneratorPort identifierGeneratorPort;

    @Override
    public User signup(SignupUserCommand signupUserCommand) {
        if (!emailVerificationPort.isVerified(signupUserCommand.getEmail())) {
            throw BusinessRuleException.of(ErrorCodeEnum.EMAIL_NOT_VERIFIED);
        }
        String userGuid = identifierGeneratorPort.generate();
        String encodedPassword = passwordPolicyPort.encode(signupUserCommand.getPassword());
        User user = User.createGeneralUser(userGuid, signupUserCommand.getEmail(), signupUserCommand.getUsername(), encodedPassword);
        emailVerificationPort.delete(signupUserCommand.getEmail());
        return userPort.save(user);
    }

    @Override
    public void createAdminUser(String email, String username, String rawPassword) {
        String userGuid = identifierGeneratorPort.generate();
        String encodedPassword = passwordPolicyPort.encode(rawPassword);
        User adminUser = User.createAdminUser(userGuid, email, username, encodedPassword);
        userPort.save(adminUser);
    }

    @Override
    public boolean existsByRole(UserRole role) {
        return userPort.existsByRole(role);
    }
}