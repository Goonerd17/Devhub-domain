package goonerd.devhub.ports.in;

import goonerd.devhub.adapters.in.user.command.SignupUserCommand;
import goonerd.devhub.domain.user.User;
import goonerd.devhub.domain.user.UserRole;

public interface UserUseCase {
    User signup(SignupUserCommand signupUserCommand);
    void createAdminUser(String userId, String username, String rawPassword);
    boolean existsByRole(UserRole role);
}