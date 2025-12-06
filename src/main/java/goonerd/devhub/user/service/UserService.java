package goonerd.devhub.user.service;

import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.common.enums.UserRoleEnum;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.user.dto.SignupRequestDto;
import goonerd.devhub.user.entity.User;
import goonerd.devhub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponseVo<?> signup(SignupRequestDto signupRequestDto) {

        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        UserRoleEnum role = UserRoleEnum.USER;
        checkDuplicatedUsername(username);

        User user = User.create(username, password, role);
        userRepository.save(user);
        return ApiResponseVo.success(SuccessCodeEnum.CREATE_SUCCESS, signupRequestDto, Collections.emptyMap());
    }

    private void checkDuplicatedUsername(String username) {
        Optional<User> result = userRepository.findByUsername(username);
        if (result.isPresent()) {

        }
    }
}