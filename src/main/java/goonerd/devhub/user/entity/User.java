package goonerd.devhub.user.entity;

import goonerd.devhub.common.entity.BaseEntity;
import goonerd.devhub.common.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "users")
public class User extends BaseEntity {

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

    public User(String userId, String username, String password, UserRoleEnum role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static User createLocalUser(String userId, String username, String password, UserRoleEnum role) {
        return new User(userId, username, password, role);
    }

    public static User createAdminUser(String userId, String username, String password, UserRoleEnum role) {
        return new User(userId, username, password, role);
    }
}