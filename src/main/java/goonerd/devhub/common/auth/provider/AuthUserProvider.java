package goonerd.devhub.common.auth.provider;

import goonerd.devhub.common.auth.userdetails.UserDetailsImpl;

public interface AuthUserProvider {
    UserDetailsImpl getCurrentUser();
    String getCurrentUserName();
    String getCurrentUserEmail();
}