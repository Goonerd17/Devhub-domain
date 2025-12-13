package goonerd.devhub.domain.user;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;
import goonerd.devhub.domain.common.AuditInfo;

public class User {

    private String userId;
    private String password;
    private String username;

    private UserRole role;

    private AuditInfo auditInfo;

    public User(String userId, String username, String password, UserRole role, AuditInfo auditInfo) {
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
        this.auditInfo = auditInfo != null ? auditInfo : AuditInfo.empty();
    }

    public static User createGeneralUser(String userId, String username, String password) {
        return new User(userId, username, password, UserRole.USER, AuditInfo.empty());
    }

    public static User createAdminUser(String userId, String username, String password) {
        return new User(userId, username, password, UserRole.ADMIN, AuditInfo.empty());
    }

    public boolean hasRole(UserRole checkRole) {
        return this.role == checkRole;
    }

    public User changeUsername(String newUsername) {
        return new User(this.userId, newUsername, this.password, this.role, this.auditInfo);
    }

    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public UserRole getRole() { return role; }
    public AuditInfo getAuditInfo() { return auditInfo; }
}