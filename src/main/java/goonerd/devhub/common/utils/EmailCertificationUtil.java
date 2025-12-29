package goonerd.devhub.common.utils;

import java.security.SecureRandom;

public class EmailCertificationUtil {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int CODE_LENGTH = 6;

    private EmailCertificationUtil() {}

    public static String generateEmailCertificationCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(RANDOM.nextInt(10));
        }
        return code.toString();
    }
}