package goonerd.devhub.domain.project;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProjectTest {

    Project project;

    @BeforeEach
    void initialize() {
        project = ProjectTestFixture.project();
    }

    @Test
    void 종료일이_지나지_않으면_closed는_false다() {
        Project project = Project.createNew(
                "user1",
                "작성자",
                "프로젝트 제목",
                "내용",
                1,
                RecruitmentType.NORMAL,
                ProjectProgressType.ONLINE,
                LocalDate.now(),
                LocalDate.now().plusDays(1), // 종료일이 내일
                List.of(
                        new PositionSlot("BACKEND", "JUNIOR", 1, 0)
                ),
                List.of("Java")
        );

        boolean closed = project.isClosed(LocalDate.now());

        assertThat(closed).isFalse();
    }

    @Test
    void 제목을_빈값으로_변경하면_예외가_발생한다() {
        assertThatThrownBy(() -> project.changeTitle(""))
                .isInstanceOf(DomainRuleException.class)
                .hasMessageContaining("프로젝트 제목은 공백일 수 없습니다.")
                .extracting("errorCodeEnum")
                .isEqualTo(ErrorCodeEnum.PROJECT_TITLE_FAIL);
    }
}