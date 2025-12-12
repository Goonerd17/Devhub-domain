package goonerd.devhub.adapters.out.user;

import goonerd.devhub.domain.user.User;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getUserId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getRole()
        );
    }
}
