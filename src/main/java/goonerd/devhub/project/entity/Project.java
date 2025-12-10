package goonerd.devhub.project.entity;

import goonerd.devhub.common.entity.BaseEntity;
import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;
import goonerd.devhub.project.command.ProjectCreateCommand;
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
    private String projectGuid;
    @Column
    private String userId;
    @Column
    private String username;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private int recruitCount;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;

    private Project(ProjectCreateCommand projectCreateCommand) {
        validateRecruitCount(projectCreateCommand);
        validateProjectPeriod(projectCreateCommand);
        validateProjectDescription(projectCreateCommand);
        this.userId = projectCreateCommand.getUserId();
        this.title = projectCreateCommand.getTitle();
        this.username = projectCreateCommand.getUsername();
        this.description = projectCreateCommand.getDescription();
        this.recruitCount = projectCreateCommand.getRecruitCount();
        this.startDate = projectCreateCommand.getStartDate();
        this.endDate = projectCreateCommand.getEndDate();
    }

    public static Project create(ProjectCreateCommand projectCreateCommand) {
        return new Project(projectCreateCommand);
    }

    private void validateRecruitCount(ProjectCreateCommand projectCreateCommand) {
        if (projectCreateCommand.getRecruitCount() < 1) {
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_RECRUITMENT_FAIL);
        }
    }

    private void validateProjectPeriod(ProjectCreateCommand projectCreateCommand) {
        if (projectCreateCommand.getEndDate().isBefore(projectCreateCommand.getStartDate())) {
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_PERIOD_FAIL);
        }
    }

    private void validateProjectDescription(ProjectCreateCommand projectCreateCommand) {
        if (projectCreateCommand.getDescription().length() > 100) {
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_DESCRIPTION_FAIL);
        }
    }
}