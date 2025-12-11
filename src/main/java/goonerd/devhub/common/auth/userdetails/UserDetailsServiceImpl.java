package goonerd.devhub.common.auth.userdetails;

import goonerd.devhub.adapters.out.user.UserEntity;
import goonerd.devhub.adapters.out.user.UserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepositoryJpa userRepositoryJpa;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserEntity userEntity = userRepositoryJpa.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 " + userId + "은 존재하지 않습니다"));

        return new UserDetailsImpl(userEntity);
    }
}