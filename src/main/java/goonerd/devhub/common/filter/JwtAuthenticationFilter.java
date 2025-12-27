package goonerd.devhub.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import goonerd.devhub.adapters.in.user.dto.LoginUserRequestDto;
import goonerd.devhub.adapters.in.common.vo.ApiResponseVo;
import goonerd.devhub.application.auth.RefreshTokenService;
import goonerd.devhub.common.auth.userdetails.UserDetailsImpl;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.common.utils.JwtUtil;
import goonerd.devhub.domain.user.UserRole;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            LoginUserRequestDto loginUserRequestDto =
                    objectMapper.readValue(request.getInputStream(), LoginUserRequestDto.class);

            return getAuthenticationManager().authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginUserRequestDto.getEmail(),
                                    loginUserRequestDto.getPassword()));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String email = userDetails.getUsername();
        UserRole role = userDetails.getUser().getRole();

        String accessToken = jwtUtil.createAccessToken(email, role);
        String refreshToken = jwtUtil.createRefreshToken(email);
        refreshTokenService.save(email, refreshToken);

        ApiResponseVo<?> responseBody = ApiResponseVo.successWithData(SuccessCodeEnum.LOGIN_SUCCESS, Map.of("accessToken", accessToken, "refreshToken", refreshToken));

        String jsonResponse = objectMapper.writeValueAsString(responseBody);

        response.setHeader(HttpHeaders.AUTHORIZATION, JwtUtil.BEARER_PREFIX + accessToken);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        ApiResponseVo<?> apiResponse = ApiResponseVo.failureWithoutData(ErrorCodeEnum.LOGIN_FAIL);
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}