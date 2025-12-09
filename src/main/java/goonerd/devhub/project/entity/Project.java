package goonerd.devhub.project.entity;

import goonerd.devhub.common.entity.BaseEntity;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;
import goonerd.devhub.project.dto.ProjectCreateRequestDto;
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
public class Project extends BaseEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(length = 36, nullable = false, unique = true)
    private String projectId;
    @Column
    private String username;
    @Column
    private String description;
    @Column
    private int recruitCount;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;

    private Project(ProjectCreateRequestDto projectCreateRequestDto) {
        validateRecruitCount(projectCreateRequestDto);
        validateProjectPeriod(projectCreateRequestDto);
        validateProjectDescription(projectCreateRequestDto);
        this.username = projectCreateRequestDto.getUsername();
        this.description = projectCreateRequestDto.getDescription();
        this.recruitCount = projectCreateRequestDto.getRecruitCount();
        this.startDate = projectCreateRequestDto.getStartDate();
        this.endDate = projectCreateRequestDto.getEndDate();
    }

    public static Project create(ProjectCreateRequestDto projectCreateRequestDto) {
        return new Project(projectCreateRequestDto);
    }

    private void validateRecruitCount(ProjectCreateRequestDto projectCreateRequestDto) {
        if (projectCreateRequestDto.getRecruitCount() < 1) {
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_RECRUITCOUNT_FAIL);
        }
    }

    private void validateProjectPeriod(ProjectCreateRequestDto projectCreateRequestDto) {
        if (projectCreateRequestDto.getEndDate().isBefore(projectCreateRequestDto.getStartDate())) {
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_PERIOD_FAIL);
        }
    }

    private void validateProjectDescription(ProjectCreateRequestDto projectCreateRequestDto) {
        if (projectCreateRequestDto.getDescription().length() > 100) {
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_DESCRIPTION_FAIL);
        }
    }
}