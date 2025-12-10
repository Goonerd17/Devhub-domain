package goonerd.devhub.common.filter;

import goonerd.devhub.common.auth.userdetails.UserDetailsServiceImpl;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.enums.JwtStatusEnum;
import goonerd.devhub.common.component.CustomFilterExceptionHandler;
import goonerd.devhub.common.exception.JwtAuthenticationException;
import goonerd.devhub.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j(topic = "JWT 검증, 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final CustomFilterExceptionHandler customFilterExceptionHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {

        String token = jwtUtil.getTokenFromHeader(req);

        // 토큰이 없으면 인증 없이 다음 필터로 진행
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(req, res);
            return;
        }

        String pureToken = jwtUtil.substringHeaderToken(token);
        JwtStatusEnum status = jwtUtil.validateToken(pureToken);

        try {
            switch (status) {
                case EXPIRED:
                    throw JwtAuthenticationException.of(ErrorCodeEnum.TOKEN_EXPIRED);
                case INVALID:
                    throw JwtAuthenticationException.of(ErrorCodeEnum.TOKEN_INVALID);
                case VALID:
                    Claims claims = jwtUtil.getUserInfo(pureToken);
                    setAuthentication(claims.getSubject());
                    break;
            }
            filterChain.doFilter(req, res);
        } catch (JwtAuthenticationException e) {
            customFilterExceptionHandler.handle(res, e.getErrorCodeEnum());
        } catch (JwtException e) {
            customFilterExceptionHandler.handle(res, e);
        } catch (Exception e) {
            customFilterExceptionHandler.handle(res, e);
        }
    }

    public void setAuthentication(String userId) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(userId);
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private Authentication createAuthentication(String userId) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }
}