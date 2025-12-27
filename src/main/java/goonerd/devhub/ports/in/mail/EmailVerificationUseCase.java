package goonerd.devhub.ports.in.mail;

import goonerd.devhub.adapters.in.auth.command.ConfirmEmailVerificationCommand;
import goonerd.devhub.adapters.in.auth.dto.EmailVerificationRequestDto;

public interface EmailVerificationUseCase {
    void requestEmailVerificationCode(EmailVerificationRequestDto emailVerificationRequestDto);
    void verifyEmailVerificationCode(ConfirmEmailVerificationCommand confirmEmailVerificationCommand);
    boolean isVerified(String email);
    void delete(String email);
}
