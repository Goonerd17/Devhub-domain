package goonerd.devhub.common.auth.service;

import goonerd.devhub.common.auth.dto.TokenResponseDto;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.enums.JwtStatusEnum;
import goonerd.devhub.common.exception.AuthRuleException;
import goonerd.devhub.common.utils.JwtUtil;
import goonerd.devhub.user.dto.LoginRequestDto;
import goonerd.devhub.user.entity.User;
import goonerd.devhub.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RefreshTokenService refreshTokenService;

    public TokenResponseDto login(LoginRequestDto req) {
        User user = userRepository.findByUserId(req.getUserId())
                .orElseThrow(() -> AuthRuleException.of(ErrorCodeEnum.LOGIN_FAIL));

        String accessToken = jwtUtil.createAccessToken(user.getUserId(), user.getRole());
        String refreshToken = jwtUtil.createRefreshToken(user.getUserId());

        refreshTokenService.save(user.getUserId(), refreshToken);

        return new TokenResponseDto(accessToken, refreshToken);
    }

    public TokenResponseDto reissue(String refreshToken) {
        refreshToken = jwtUtil.removeBearer(refreshToken);

        JwtStatusEnum status = jwtUtil.validateToken(refreshToken);
        if (status != JwtStatusEnum.VALID) {
            throw AuthRuleException.of(ErrorCodeEnum.REFRESH_TOKEN_INVALID);
        }

        Claims claims = jwtUtil.getUserInfo(refreshToken);
        String userId = claims.getSubject();

        String stored = refreshTokenService.findByUserId(userId);
        if (!stored.equals("Bearer " + refreshToken)) {
            throw AuthRuleException.of(ErrorCodeEnum.REFRESH_TOKEN_MISMATCH);
        }

        User user = userRepository.findByUserId(userId)
                .orElseThrow();

        String newAccessToken = jwtUtil.createAccessToken(userId, user.getRole());

        return new TokenResponseDto(newAccessToken, refreshToken);
    }
}