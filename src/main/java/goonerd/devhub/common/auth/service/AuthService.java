package goonerd.devhub.common.auth.service;

import goonerd.devhub.adapters.out.user.UserEntity;
import goonerd.devhub.common.auth.dto.TokenResponseDto;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.enums.JwtStatusEnum;
import goonerd.devhub.common.exception.AuthRuleException;
import goonerd.devhub.common.utils.JwtUtil;
import goonerd.devhub.adapters.in.user.dto.LoginRequestDto;
import goonerd.devhub.adapters.out.user.UserRepositoryJpa;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepositoryJpa userRepositoryJpa;
    private final RefreshTokenService refreshTokenService;

    public TokenResponseDto login(LoginRequestDto req) {
        UserEntity userEntity = userRepositoryJpa.findByUserId(req.getUserId())
                .orElseThrow(() -> AuthRuleException.of(ErrorCodeEnum.LOGIN_FAIL));

        String accessToken = jwtUtil.createAccessToken(userEntity.getUserId(), userEntity.getRole());
        String refreshToken = jwtUtil.createRefreshToken(userEntity.getUserId());

        refreshTokenService.save(userEntity.getUserId(), refreshToken);

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

        UserEntity userEntity = userRepositoryJpa.findByUserId(userId)
                .orElseThrow();

        String newAccessToken = jwtUtil.createAccessToken(userId, userEntity.getRole());

        return new TokenResponseDto(newAccessToken, refreshToken);
    }
}