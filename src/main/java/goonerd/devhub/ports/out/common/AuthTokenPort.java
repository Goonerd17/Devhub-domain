package goonerd.devhub.ports.out.common;

import goonerd.devhub.common.enums.JwtStatusEnum;
import goonerd.devhub.domain.user.UserRole;

public interface AuthTokenPort {

    JwtStatusEnum validateToken(String token);
    String getEmailFromRefreshToken(String refreshToken);
    String createAccessToken(String email, UserRole userRole);
}
