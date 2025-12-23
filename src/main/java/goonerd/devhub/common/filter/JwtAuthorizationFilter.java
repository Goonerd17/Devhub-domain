package goonerd.devhub.common.filter;

import goonerd.devhub.common.auth.userdetails.UserDetailsServiceImpl;
import goonerd.devhub.common.component.JwtAuthenticationProvider;
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

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CustomFilterExceptionHandler customFilterExceptionHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.getTokenFromHeader(httpServletRequest);

        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        String pureToken = jwtUtil.substringHeaderToken(token);
        JwtStatusEnum status = jwtUtil.validateToken(pureToken);

        if (status == JwtStatusEnum.EXPIRED) {
            customFilterExceptionHandler.handle(httpServletResponse, ErrorCodeEnum.TOKEN_EXPIRED);
            return;
        }

        if (status == JwtStatusEnum.INVALID) {
            customFilterExceptionHandler.handle(httpServletResponse, ErrorCodeEnum.TOKEN_INVALID);
            return;
        }

        Claims claims = jwtUtil.getUserInfo(pureToken);
        setAuthentication(claims.getSubject());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    public void setAuthentication(String userId) {
        Authentication authentication = jwtAuthenticationProvider.authenticate(userId);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}