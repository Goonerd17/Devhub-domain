package goonerd.devhub.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import goonerd.devhub.common.auth.service.RefreshTokenService;
import goonerd.devhub.common.auth.userdetails.UserDetailsImpl;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.common.enums.UserRoleEnum;
import goonerd.devhub.common.utils.JwtUtil;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.user.dto.LoginRequestDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, RefreshTokenService refreshTokenService) {
        this.jwtUtil = jwtUtil;
        this.refreshTokenService = refreshTokenService;
        setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            LoginRequestDto loginRequestDto =
                    new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

            return getAuthenticationManager().authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginRequestDto.getUserId(),
                                    loginRequestDto.getPassword()));
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

        ObjectMapper objectMapper = new ObjectMapper();
        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String userId = userDetails.getUsername();
        UserRoleEnum role = userDetails.getUser().getRole();

        String accessToken = jwtUtil.substringHeaderToken(jwtUtil.createAccessToken(userId, role));
        String refreshToken = jwtUtil.substringHeaderToken(jwtUtil.createRefreshToken(userId));
        refreshTokenService.save(userId, refreshToken);

        // 응답 JSON 생성
        ApiResponseVo<?> responseBody = ApiResponseVo.successWithData(
                SuccessCodeEnum.LOGIN_SUCCESS,
                Map.of("accessToken", accessToken, "refreshToken", refreshToken));

        String jsonResponse = objectMapper.writeValueAsString(responseBody);

        // 응답 헤더에 AccessToken 넣기
        jwtUtil.addJwtHeader(accessToken, response);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        ApiResponseVo<?> apiResponse = ApiResponseVo.failureWithoutParam(ErrorCodeEnum.LOGIN_FAIL);
        String jsonResponse = objectMapper.writeValueAsString(apiResponse);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}