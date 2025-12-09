package goonerd.devhub.common.auth.provider;

import goonerd.devhub.common.auth.userdetails.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUserProviderImpl implements AuthUserProvider {

    private UserDetailsImpl getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
            throw new IllegalStateException("로그인된 사용자가 존재하지 않습니다.");
        }

        return (UserDetailsImpl) authentication.getPrincipal();
    }

    public String getCurrentUserName() {
        return getPrincipal().getUsername();
    }

    public String getCurrentUserEmail() {
        return getPrincipal().getUsername();
    }

    public UserDetailsImpl getCurrentUser() {
        return getPrincipal();
    }

}