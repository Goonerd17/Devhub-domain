package goonerd.devhub.domain.project;

import goonerd.devhub.common.enums.ErrorCodeEnum;
import goonerd.devhub.common.exception.DomainRuleException;
import goonerd.devhub.domain.common.AuditInfo;
import goonerd.devhub.domain.application.Application;
import goonerd.devhub.domain.position.Position;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Project {

    private final String projectGuid;

    private final String authorGuid;
    private String authorName;

    private String title;
    private String content;

    private RecruitmentType recruitmentType;
    private ProgressType progressType;

    private LocalDate startDate;
    private LocalDate endDate;

    private List<Position> positionList;
    private List<String> skillList;

    private int recruitCount;
    private int likes;
    private AuditInfo auditInfo;

    private Project(
            String projectGuid,
            String authorGuid,
            String authorName,
            String title,
            String content,
            RecruitmentType recruitmentType,
            ProgressType progressType,
            LocalDate startDate,
            LocalDate endDate,
            List<Position> positionList,
            List<String> skillList,
            int recruitCount,
            int likes,
            AuditInfo auditInfo
    ) {
        if (title == null || title.isBlank())
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_TITLE_FAIL);
        if (recruitCount < 0)
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_POSITION_RECRUITMENT_FAIL);

        this.projectGuid = projectGuid;
        this.authorGuid = authorGuid;
        this.authorName = authorName;
        this.title = title;
        this.content = content;
        this.recruitmentType = Objects.requireNonNull(recruitmentType);
        this.progressType = Objects.requireNonNull(progressType);
        this.startDate = Objects.requireNonNull(startDate);
        this.endDate = Objects.requireNonNull(endDate);
        this.positionList = positionList != null ? new ArrayList<>(positionList) : new ArrayList<>();
        this.skillList = skillList != null ? List.copyOf(skillList) : Collections.emptyList();
        this.recruitCount = recruitCount;
        this.likes = likes;
        this.auditInfo = auditInfo != null ? auditInfo : AuditInfo.empty();
    }

    public static Project createNew(
            String projectGuid,
            String authorGuid,
            String authorName,
            String title,
            String content,
            RecruitmentType recruitmentType,
            ProgressType progressType,
            LocalDate startDate,
            LocalDate endDate,
            List<Position> positionList,
            List<String> skillList,
            int recruitCount
    ) {
        return new Project(
                projectGuid,
                authorGuid,
                authorName,
                title,
                content,
                recruitmentType,
                progressType,
                startDate,
                endDate,
                positionList,
                skillList,
                recruitCount,
                0,
                AuditInfo.empty()
        );
    }

    public static Project reconstruct(
            String projectGuid,
            String authorGuid,
            String authorName,
            String title,
            String content,
            RecruitmentType recruitmentType,
            ProgressType progressType,
            LocalDate startDate,
            LocalDate endDate,
            List<Position> positionList,
            List<String> skillList,
            int recruitCount,
            int likes,
            AuditInfo auditInfo
    ) {
        return new Project(
                projectGuid,
                authorGuid,
                authorName,
                title,
                content,
                recruitmentType,
                progressType,
                startDate,
                endDate,
                positionList,
                skillList,
                recruitCount,
                likes,
                auditInfo
        );
    }

    public ProjectStatus calculateStatus(LocalDate now) {

        if (isClosed(now)) {
            return ProjectStatus.CLOSED;
        }

        if (isCompleted()) {
            return ProjectStatus.COMPLETED;
        }

        return ProjectStatus.RECRUITING;
    }

    public boolean isClosed(LocalDate now) {
        return now.isAfter(endDate);
    }

    private boolean isCompleted() {
        return positionList.stream().allMatch(Position::isFull);
    }

    public void changeTitle(String newTitle) {
        if (newTitle == null || newTitle.isBlank()) {
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_TITLE_FAIL);
        }
        this.title = newTitle;
    }

    public void changeRecruitmentType(RecruitmentType newType) {
        this.recruitmentType = Objects.requireNonNull(newType);
    }

    public boolean canApply(String position, String level) {
        return positionList.stream()
                .filter(p -> p.getPositionName().equals(position) && p.getLevel().equals(level))
                .anyMatch(p -> !p.isFull());
    }

    public void approveApplication(Application application) {
        if (isClosed(LocalDate.now())) {
            throw DomainRuleException.of(ErrorCodeEnum.PROJECT_PERIOD_FAIL);
        }

        Position position = findPosition(application.getPositionName(), application.getSkillLevel());

        position.ensureCanApprove();
        application.approve();
        position.increaseApprovedCount();
    }

    public Position findPosition(String positionName, String skillLevel) {
        return positionList.stream()
                .filter(position ->
                        position.getPositionName().equals(positionName) && position.getLevel().equals(skillLevel))
                .findFirst()
                .orElseThrow(() ->
                        DomainRuleException.of(ErrorCodeEnum.UNKNOWN_FAIL)
                );
    }

    public String getProjectGuid() { return projectGuid; }
    public String getAuthorGuid() { return authorGuid; }
    public String getAuthorName() { return authorName; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public RecruitmentType getRecruitmentType() { return recruitmentType; }
    public ProgressType getProgressType() { return progressType; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public List<Position> getPositionList() {return List.copyOf(positionList); }
    public List<String> getSkillList() { return skillList; }
    public int getRecruitCount() { return recruitCount; }
    public int getLikes() { return likes; }
    public AuditInfo getAuditInfo() { return auditInfo; }
}