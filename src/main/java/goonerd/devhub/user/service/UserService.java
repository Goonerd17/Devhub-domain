package goonerd.devhub.user.service;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.common.enums.UserRoleEnum;
import goonerd.devhub.common.exception.BusinessRuleException;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.user.dto.SignupRequestDto;
import goonerd.devhub.user.entity.User;
import goonerd.devhub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponseVo<Map<String, Object>> signup(SignupRequestDto signupRequestDto) {

        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        UserRoleEnum role = UserRoleEnum.USER;

        checkDuplicatedUsername(username);

        User user = User.createLocalUser(username, password, role);
        userRepository.save(user);
        return ApiResponseVo.successWithParam(SuccessCodeEnum.CREATE_SUCCESS, signupRequestDto);
    }

    private void checkDuplicatedUsername(String username) {
        Optional<User> result = userRepository.findByUsername(username);
        if (result.isPresent()) {
            throw BusinessRuleException.of(ErrorCodeEnum.DUPLICATE_USERNAME);
        }
    }
}