package goonerd.devhub.domains.project;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProjectTest {

//    Project project;
//
//    @BeforeEach
//    void initialize() {
//        project = ProjectTestFixture.project();
//    }
//
//    @Test
//    void 종료일이_지나지_않으면_closed는_false다() {
//        Project project = Project.createNew(
//                "user1",
//                "작성자",
//                "프로젝트 제목",
//                "내용",
//                1,
//                RecruitmentType.NORMAL,
//                ProgressType.ONLINE,
//                LocalDate.now(),
//                LocalDate.now().plusDays(1),
//                List.of(
//                        new Position("BACKEND", 1,"JUNIOR", 0)
//                ),
//                List.of("Java")
//        );
//
//        boolean closed = project.isClosed(LocalDate.now());
//
//        assertThat(closed).isFalse();
//    }
//
//    @Test
//    void 제목을_빈값으로_변경하면_예외가_발생한다() {
//        assertThatThrownBy(() -> project.changeTitle(""))
//                .isInstanceOf(DomainRuleException.class)
//                .hasMessageContaining("프로젝트 제목은 공백일 수 없습니다.")
//                .extracting("errorCodeEnum")
//                .isEqualTo(ErrorCodeEnum.PROJECT_TITLE_FAIL);
//    }
}