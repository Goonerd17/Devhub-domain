package goonerd.devhub.domain.user;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;

public class User {

    private final String userId;
    private final String username;
    private final String password;
    private final UserRole role;

    public User(String userId, String username, String password, UserRole role) {
        if (userId == null || userId.isBlank()) {
            throw DomainRuleException.of(ErrorCodeEnum.UNKNOWN_FAIL);
        }
        if (password == null || password.length() < 6) {
            throw DomainRuleException.of(ErrorCodeEnum.UNKNOWN_FAIL);
        }
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static User createUser(String userId, String username, String password) {
        return new User(userId, username, password, UserRole.USER);
    }

    public static User createAdmin(String userId, String username, String password) {
        return new User(userId, username, password, UserRole.ADMIN);
    }

    public boolean hasRole(UserRole checkRole) {
        return this.role == checkRole;
    }

    public User changeUsername(String newUsername) {
        return new User(this.userId, newUsername, this.password, this.role);
    }

    public String getUserId() {return userId;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public UserRole getRole() {return role;}
}