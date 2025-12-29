package goonerd.devhub.ports.in.user;

import goonerd.devhub.adapters.in.user.command.SignupUserCommand;
import goonerd.devhub.domains.user.User;
import goonerd.devhub.domains.user.UserRole;
import org.springframework.security.access.prepost.PreAuthorize;

public interface UserUseCase {
    User signup(SignupUserCommand signupUserCommand);
    void createAdminUser(String email, String username, String rawPassword);
    boolean existsByRole(UserRole role);
    @PreAuthorize("@userAuthChecker.isSelf(#userGuid)")
    void update();
}