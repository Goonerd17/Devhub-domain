package goonerd.devhub.common.auth;

public interface AuthUserProvider {
    UserDetailsImpl getCurrentUser();
    String getCurrentUserName();
    String getCurrentUserEmail();
}
