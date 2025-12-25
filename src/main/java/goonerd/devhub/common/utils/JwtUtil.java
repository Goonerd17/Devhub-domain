package goonerd.devhub.common.utils;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.enums.JwtStatusEnum;
import goonerd.devhub.common.enums.TokenTypeEnum;
import goonerd.devhub.common.exception.AuthRuleException;
import goonerd.devhub.domain.user.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_KEY = "auth";
    public static final String BEARER_PREFIX = "Bearer ";
    private static final long ACCESS_TOKEN_TIME = 30 * 60 * 1000L;
    private static final long REFRESH_TOKEN_TIME = 60 * 60 * 1000L;

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String removeBearer(String token) {
        if (token.startsWith(BEARER_PREFIX)) {
            return token.substring(7);
        }
        return token;
    }

    // Header 토큰을 가져오기
    public String substringHeaderToken(String token) {
        if (StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX)) { return token.substring(7);}
        throw AuthRuleException.of(ErrorCodeEnum.TOKEN_INVALID);
    }

    // Header 안에 있는 토큰 decode
    public String getTokenFromHeader(HttpServletRequest req) {
        String token = req.getHeader(AUTHORIZATION_HEADER);
        if(token != null) {
            try {
                return URLDecoder.decode(token, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.info(e.getMessage());
                return null;
            }
        }
        return null;
    }

    // AccessToken 생성
    public String createAccessToken(String userId, UserRole role) {
        Date now = new Date();
        return Jwts.builder()
                        .setSubject(userId)
                        .claim(AUTHORIZATION_KEY, role)
                        .claim("token_type", TokenTypeEnum.ACCESS.name())
                        .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_TIME))
                        .setIssuedAt(now)
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }

    // RefreshToken 생성
    public String createRefreshToken(String userId) {
        Date now = new Date();
        return Jwts.builder()
                        .setSubject(userId)
                        .claim("token_type", TokenTypeEnum.REFRESH.name())
                        .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_TIME))
                        .setIssuedAt(now)
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }

    // 토큰 검증
    public JwtStatusEnum validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return JwtStatusEnum.VALID;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
            return JwtStatusEnum.INVALID;
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token, 만료된 JWT token 입니다.");
            return JwtStatusEnum.EXPIRED;
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
            return JwtStatusEnum.INVALID;
        } catch (IllegalArgumentException e) {
            log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
            return JwtStatusEnum.INVALID;
        } finally {
            log.info("JWT 토큰 검증 완료");
        }
    }

    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfo(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String getUserIdFromRefreshToken(String refreshToken) {
        Claims claims = getUserInfo(refreshToken);
        TokenTypeEnum tokenTypeEnum = TokenTypeEnum.valueOf(claims.get("token_type", String.class));
        if (tokenTypeEnum != TokenTypeEnum.REFRESH) {
            throw AuthRuleException.of(ErrorCodeEnum.TOKEN_INVALID);
        }
        return claims.getSubject();
    }
}