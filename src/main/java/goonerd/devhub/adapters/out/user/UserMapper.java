package goonerd.devhub.adapters.out.user;

import goonerd.devhub.adapters.out.user.entity.UserEntity;
import goonerd.devhub.domains.common.AuditInfo;
import goonerd.devhub.domains.user.User;

public class UserMapper {

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
                .userGuid(user.getUserGuid())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    public static User toDomain(UserEntity userEntity) {
        return User.of(
                userEntity.getUserGuid(),
                userEntity.getEmail(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRole(),
                toAuditInfo(userEntity)
        );
    }

    private static AuditInfo toAuditInfo(UserEntity userEntity) {
        return AuditInfo.of(
                userEntity.getCreatedBy(),
                userEntity.getCreatedAt(),
                userEntity.getModifiedBy(),
                userEntity.getModifiedAt()
        );
    }
}