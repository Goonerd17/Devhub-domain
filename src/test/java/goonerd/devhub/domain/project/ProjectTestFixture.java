package goonerd.devhub.domain.project;

import java.time.LocalDate;
import java.util.List;

class ProjectTestFixture {

    static Project project() {
        return Project.createNew(
                "user1",
                "테스트유저",
                "AI 프로젝트 제목",
                "test 프로젝트 내용입니다.",
                1,
                RecruitmentType.NORMAL,
                ProjectProgressType.ONLINE,
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                List.of(
                        new PositionSlot("BACKEND", "JUNIOR", 1, 0)
                ),
                List.of("Java")
        );
    }
}
