package goonerd.devhub.ports.out.common;

public interface PasswordPolicyPort {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String hashedPassword);
}