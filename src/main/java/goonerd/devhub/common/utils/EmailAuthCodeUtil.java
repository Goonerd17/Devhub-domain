package goonerd.devhub.common.utils;

import java.security.SecureRandom;

public class EmailAuthCodeUtil {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int CODE_LENGTH = 6;

    private EmailAuthCodeUtil() {}

    public static String generateEmailAuthCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(RANDOM.nextInt(10)); // 0 ~ 9
        }
        return code.toString();
    }
}