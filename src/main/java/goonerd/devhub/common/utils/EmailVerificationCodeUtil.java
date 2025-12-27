package goonerd.devhub.common.utils;

import java.security.SecureRandom;

public class EmailVerificationCodeUtil {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int CODE_LENGTH = 6;

    private EmailVerificationCodeUtil() {}

    public static String generateEmailVerificationCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(RANDOM.nextInt(10));
        }
        return code.toString();
    }
}