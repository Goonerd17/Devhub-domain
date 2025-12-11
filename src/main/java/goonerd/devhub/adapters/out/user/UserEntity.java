package goonerd.devhub.adapters.out.user;

import goonerd.devhub.common.entity.BaseEntity;
import goonerd.devhub.domain.user.UserRoleEnum;
import goonerd.devhub.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36, nullable = false, unique = true)
    private String userGuid;
    @Column(unique = true)
    private String userId;
    @Column(unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    // Domain → Entity 변환
    public static UserEntity createUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.userId = user.getUserId();
        entity.username = user.getUsername();
        entity.password = user.getPassword();
        entity.role = user.getRole();
        return entity;
    }

    // Entity → Domain 변환
    public User createUserDomain() {
        return new User(userId, username, password, role);
    }
}