package goonerd.devhub.service.facade.application;

import goonerd.devhub.adapters.in.application.command.ApplyApplicationCommand;
import goonerd.devhub.adapters.in.application.dto.ApplyApplicationResponseDto;
import goonerd.devhub.common.enums.SuccessCodeEnum;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.domain.application.Application;
import goonerd.devhub.ports.in.ApplicationUseCase;
import goonerd.devhub.service.application.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationFacade implements ApplicationUseCase {

    private final ApplicationService applicationService;

    public ApiResponseVo<ApplyApplicationResponseDto> apply (ApplyApplicationCommand applyApplicationCommand) {
        Application application = applicationService.apply(applyApplicationCommand);
        ApplyApplicationResponseDto applicationResponseDto = ApplicationResponseDto.fromDomain(application);
        return ApiResponseVo.successWithParamAndData(SuccessCodeEnum.CREATE_SUCCESS, applyApplicationCommand, applicationResponseDto);
    }
}
