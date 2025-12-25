package goonerd.devhub.ports.in;

import goonerd.devhub.adapters.in.mail.command.ConfirmEmailVerificationCommand;
import goonerd.devhub.adapters.in.mail.dto.EmailVerificationRequestDto;

public interface EmailVerificationUseCase {
    void requestEmailVerificationCode(EmailVerificationRequestDto emailVerificationRequestDto);
    void verifyEmailVerificationCode(ConfirmEmailVerificationCommand confirmEmailVerificationCommand);
    boolean isVerified(String email);
    void delete(String email);
}
