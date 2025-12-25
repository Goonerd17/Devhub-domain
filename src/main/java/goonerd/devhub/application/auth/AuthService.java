package goonerd.devhub.application.auth;

import goonerd.devhub.adapters.in.auth.dto.TokenResponseDto;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.enums.JwtStatusEnum;
import goonerd.devhub.common.exception.AuthRuleException;
import goonerd.devhub.common.utils.JwtUtil;
import goonerd.devhub.domain.user.User;
import goonerd.devhub.ports.in.auth.AuthUseCase;
import goonerd.devhub.ports.out.auth.AuthRepository;
import goonerd.devhub.ports.out.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final JwtUtil jwtUtil;
    private final AuthRepository authRepository;
    private final UserRepository userRepository;

    @Override
    public TokenResponseDto refreshAccessToken(String refreshToken) {

        JwtStatusEnum jwtStatusEnum = jwtUtil.validateToken(refreshToken);

        if (jwtStatusEnum == JwtStatusEnum.EXPIRED) {
            throw AuthRuleException.of(ErrorCodeEnum.TOKEN_EXPIRED);
        }

        if (jwtStatusEnum != JwtStatusEnum.VALID) {
            throw AuthRuleException.of(ErrorCodeEnum.REFRESH_TOKEN_INVALID);
        }

        String userId = jwtUtil.getUserIdFromRefreshToken(refreshToken);
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> AuthRuleException.of(ErrorCodeEnum.USER_NOT_FOUND));

        authRepository.findByUserId(userId)
                .filter(token -> token.getRefreshToken().equals(refreshToken))
                .orElseThrow(() -> AuthRuleException.of(ErrorCodeEnum.REFRESH_TOKEN_INVALID));

        String newAccessToken = jwtUtil.createAccessToken(user.getUserId(), user.getRole());
        return TokenResponseDto.reissue(newAccessToken, refreshToken);
    }
}