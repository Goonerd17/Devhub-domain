package goonerd.devhub.common.auth.provider;

import goonerd.devhub.common.auth.userdetails.UserDetailsImpl;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.AuthRuleException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUserProviderImpl implements AuthUserProvider {

    private UserDetailsImpl getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
            throw AuthRuleException.of(ErrorCodeEnum.USER_NOT_FOUND);
        }

        return (UserDetailsImpl) authentication.getPrincipal();
    }

    public String getCurrentUserId() {
        return getPrincipal().getUserId();
    }

    public String getCurrentUsername() {
        return getPrincipal().getUsername();
    }

    public String getCurrentUserEmail() {
        return getPrincipal().getUsername();
    }

    public UserDetailsImpl getCurrentUser() {
        return getPrincipal();
    }
}