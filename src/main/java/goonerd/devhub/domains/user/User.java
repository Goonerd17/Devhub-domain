package goonerd.devhub.domains.user;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;
import goonerd.devhub.domains.common.AuditInfo;

public class User {

    private final String userGuid;

    private final String email;
    private final String password;
    private final String username;
    private final UserRole role;

    private final AuditInfo auditInfo;

    public User(String userGuid, String email, String username, String password, UserRole role, AuditInfo auditInfo) {
        if (email == null || email.isBlank()) {
            throw DomainRuleException.of(ErrorCodeEnum.USER_ID_FAIL);
        }
        if (password == null || password.length() < 6) {
            throw DomainRuleException.of(ErrorCodeEnum.USER_PASSWORD_FAIL);
        }

        this.userGuid = userGuid;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.auditInfo = auditInfo != null ? auditInfo : AuditInfo.empty();
    }

    public static User createGeneralUser(String userGuid, String email, String username, String password) {
        return new User(userGuid, email, username, password, UserRole.USER, AuditInfo.empty());
    }

    public static User createAdminUser(String userGuid, String email, String username, String password) {
        return new User(userGuid, email, username, password, UserRole.ADMIN, AuditInfo.empty());
    }

    public static User of(String userGuid, String email, String username, String password, UserRole role, AuditInfo auditInfo) {
        return new User(userGuid, email, username, password, role, auditInfo);
    }

    public boolean hasRole(UserRole checkRole) {
        return this.role == checkRole;
    }

    public User changeUsername(String newUsername) {
        return new User(this.userGuid, this.email, newUsername, this.password, this.role, this.auditInfo);
    }

    public String getUserGuid() { return userGuid; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public UserRole getRole() { return role; }
    public AuditInfo getAuditInfo() { return auditInfo; }
}