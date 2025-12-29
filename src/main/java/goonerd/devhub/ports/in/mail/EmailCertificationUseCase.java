package goonerd.devhub.ports.in.mail;

import goonerd.devhub.adapters.in.auth.command.ConfirmEmailCertificationCommand;
import goonerd.devhub.adapters.in.auth.dto.EmailCertificationRequestDto;

public interface EmailCertificationUseCase {
    void sendEmailCertificationCode(EmailCertificationRequestDto emailCertificationRequestDto);
    void confirmEmailCertificationCode(ConfirmEmailCertificationCommand confirmEmailCertificationCommand);
    boolean isVerified(String email);
    void delete(String email);
}
