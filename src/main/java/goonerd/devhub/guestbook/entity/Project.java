package goonerd.devhub.guestbook.entity;

import goonerd.devhub.common.vo.CommonRequestVo;
import goonerd.devhub.guestbook.dto.ProjectRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project extends CommonRequestVo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String username;
    @Column
    private String description;
    @Column
    private int recruitCount;
    @Column
    private int viewCount;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;

    private Project(ProjectRequestDto projectRequestDto) {
    }

    private void validateUsernameIsDuplicated(ProjectRequestDto projectRequestDto) {

    }
}