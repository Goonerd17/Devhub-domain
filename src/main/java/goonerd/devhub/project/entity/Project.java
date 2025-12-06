package goonerd.devhub.project.entity;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainException;
import goonerd.devhub.common.vo.CommonRequestVo;
import goonerd.devhub.project.dto.ProjectRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project extends CommonRequestVo {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36, nullable = false, unique = true)
    private String id;
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
        validateRecruitCount(projectRequestDto);
        validateProjectPeriod(projectRequestDto);
        this.username = projectRequestDto.getUsername();
        this.description = projectRequestDto.getDescription();
        this.recruitCount = projectRequestDto.getRecruitCount();
        this.viewCount = projectRequestDto.getViewCount();
        this.startDate = projectRequestDto.getStartDate();
        this.endDate = projectRequestDto.getEndDate();
    }

    public static Project create(ProjectRequestDto projectRequestDto) {
        return new Project(projectRequestDto);
    }

    private void validateRecruitCount(ProjectRequestDto projectRequestDto) {
        if(projectRequestDto.getRecruitCount() < 1) {
            throw DomainException.of(ErrorCodeEnum.PROJECT_RECRUITCOUNT_FAIL);
        }
    }

    private void validateProjectPeriod(ProjectRequestDto projectRequestDto) {
        if(projectRequestDto.getEndDate().isBefore(projectRequestDto.getStartDate())) {
            throw DomainException.of(ErrorCodeEnum.PROJECT_PERIOD_FAIL);
        }
    }

    private void validateProjectDescription(ProjectRequestDto projectRequestDto) {
        if(projectRequestDto.getDescription().length() > 100) {
            throw DomainException.of(ErrorCodeEnum.PROJECT_PERIOD_FAIL);
        }
    }
}