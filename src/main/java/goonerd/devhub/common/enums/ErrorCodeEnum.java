package goonerd.devhub.common.enums;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {

    PROJECT_READ_FAIL("ERR.DVH.001", "조회 오류"),
    PROJECT_CREATE_FAIL("ERR.DVH.002", "작성 오류"),
    PROJECT_UPDATE_FAIL("ERR.DVH.003", "수정 오류"),
    PROJECT_DELETE_FAIL("ERR.DVH.004", "삭제 오류"),

    PROJECT_RECRUITCOUNT_FAIL("ERR.DVH.051", "모집 인원은 최소 1명 이상이어야 합니다."),
    PROJECT_PERIOD_FAIL("ERR.DVH.052", "프로젝트 종료일은 시작일 이후여야 합니다."),
    PROJECT_DESCRIPTION_FAIL("ERR.DVH.052", "프로젝트 설명은 100자를 넘길 수 없습니다.");

    private final String code;
    private final String message;

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
