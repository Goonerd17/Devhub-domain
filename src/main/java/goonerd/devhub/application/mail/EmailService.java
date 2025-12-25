package goonerd.devhub.application.mail;

import goonerd.devhub.adapters.in.mail.command.ConfirmEmailVerificationCommand;
import goonerd.devhub.adapters.in.mail.dto.EmailVerificationRequestDto;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.AuthRuleException;
import goonerd.devhub.common.utils.EmailAuthCodeUtil;
import goonerd.devhub.ports.in.EmailVerificationUseCase;
import goonerd.devhub.ports.out.EmailVerificationCodeRepository;
import goonerd.devhub.ports.out.EmailSendPort;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class EmailService implements EmailVerificationUseCase {

    private final EmailSendPort emailSendPort;
    private final EmailVerificationCodeRepository emailVerificationRepository;

    @Override
    public void requestEmailVerificationCode(EmailVerificationRequestDto emailVerificationRequestDto) {
        String email = emailVerificationRequestDto.getEmail();
        if (emailVerificationRepository.existsValidCode(email)) {
            throw AuthRuleException.of(ErrorCodeEnum.UNKNOWN_FAIL);
        }

        String code = EmailAuthCodeUtil.generateEmailAuthCode();
        emailVerificationRepository.save(email, code, Duration.ofMinutes(5));
        emailSendPort.sendEmail(email, "[회원가입] 이메일 인증 코드", buildBody(code));
    }

    @Override
    public void verifyEmailVerificationCode(ConfirmEmailVerificationCommand confirmEmailVerificationCommand) {
        boolean verified = emailVerificationRepository.verify(confirmEmailVerificationCommand.getEmail(), confirmEmailVerificationCommand.getCode());
        if (!verified) {
            throw AuthRuleException.of(ErrorCodeEnum.UNKNOWN_FAIL);
        }
    }

    @Override
    public boolean isVerified(String email) {
        return emailVerificationRepository.isVerified(email);
    }

    @Override
    public void delete(String email) {
        emailVerificationRepository.delete(email);
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