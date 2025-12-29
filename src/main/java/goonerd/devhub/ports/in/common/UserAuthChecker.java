package goonerd.devhub.ports.in.common;

import goonerd.devhub.common.utils.SecurityUtil;
import goonerd.devhub.ports.out.user.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("userAuthChecker")
@RequiredArgsConstructor
public class UserAuthChecker {

    private final UserPort userPort;

    public boolean isSelf(String userGuid) {
        String currentUserGuid = SecurityUtil.getCurrentUserGuid();
        return userPort.isSameUser(userGuid, currentUserGuid);
    }
}
