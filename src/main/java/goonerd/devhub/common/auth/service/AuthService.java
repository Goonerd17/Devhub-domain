package goonerd.devhub.common.auth.service;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.enums.JwtStatusEnum;
import goonerd.devhub.common.exception.AuthRuleException;
import goonerd.devhub.common.utils.JwtUtil;
import goonerd.devhub.domain.user.User;
import goonerd.devhub.ports.in.AuthUseCase;
import goonerd.devhub.ports.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    @Override
    public String refreshAccessToken(String refreshToken) {

        JwtStatusEnum status = jwtUtil.validateToken(refreshToken);

        if (status == JwtStatusEnum.EXPIRED) {
            throw AuthRuleException.of(ErrorCodeEnum.TOKEN_EXPIRED);
        }

        if (status != JwtStatusEnum.VALID) {
            throw AuthRuleException.of(ErrorCodeEnum.REFRESH_TOKEN_INVALID);
        }

        String userId = jwtUtil.getUserInfo(refreshToken).getSubject();
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> AuthRuleException.of(ErrorCodeEnum.USER_NOT_FOUND));

        refreshTokenService.findByUserId(userId)
                .filter(token -> token.getRefreshToken().equals(refreshToken))
                .orElseThrow(() -> AuthRuleException.of(ErrorCodeEnum.REFRESH_TOKEN_INVALID));
        return jwtUtil.createAccessToken(user.getUserId(), user.getRole());
    }
}