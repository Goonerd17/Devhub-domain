package goonerd.devhub.applications.mail;

import goonerd.devhub.adapters.in.auth.command.ConfirmEmailCertificationCommand;
import goonerd.devhub.adapters.in.auth.dto.EmailCertificationRequestDto;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.AuthRuleException;
import goonerd.devhub.common.utils.EmailCertificationUtil;
import goonerd.devhub.ports.in.mail.EmailCertificationUseCase;
import goonerd.devhub.ports.out.mail.EmailCertificationPort;
import goonerd.devhub.ports.out.mail.EmailSendPort;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailService implements EmailCertificationUseCase {

    private final EmailSendPort emailSendPort;
    private final EmailCertificationPort emailCertificationPort;

    @Override
    public void sendEmailCertificationCode(EmailCertificationRequestDto emailCertificationRequestDto) {
        String email = emailCertificationRequestDto.getEmail();
        if (emailCertificationPort.existsValidCode(email)) {
            throw AuthRuleException.of(ErrorCodeEnum.EMAIL_CERTIFICATION_CODE_ALREADY_SENT);
        }

        String code = EmailCertificationUtil.generateEmailCertificationCode();
        emailCertificationPort.save(email, code, Duration.ofMinutes(5));
        //emailSendPort.sendEmail(email, "[회원가입] 이메일 인증 코드", buildBody(code));
    }

    @Override
    public void confirmEmailCertificationCode(ConfirmEmailCertificationCommand confirmEmailCertificationCommand) {
        boolean verified = emailCertificationPort.verify(confirmEmailCertificationCommand.getEmail(), confirmEmailCertificationCommand.getCode());
        if (!verified) {
            throw AuthRuleException.of(ErrorCodeEnum.EMAIL_NOT_CONFIRMED);
        }
    }

    @Override
    public boolean isVerified(String email) {
        return emailCertificationPort.isVerified(email);
    }

    @Override
    public void delete(String email) {
        emailCertificationPort.delete(email);
    }

    private String buildBody(String code) {
        return """
            <h2>이메일 인증 코드</h2>
            <p>아래 인증 코드를 입력해주세요.</p>
            <h1>%s</h1>
            <p>유효시간은 5분입니다.</p>
            """.formatted(code);
    }
}