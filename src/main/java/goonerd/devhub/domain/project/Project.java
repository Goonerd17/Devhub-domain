package goonerd.devhub.domain.project;

import goonerd.devhub.domain.common.AuditInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Project {

    private final String projectGuid;

    private final String authorId;
    private String authorName;

    private String title;
    private String description;

    private int recruitCount;
    private int likes;

    private RecruitmentType recruitmentType;
    private ProjectStatus projectStatus;
    private ProjectProgressType projectProgressType;

    private LocalDate startDate;
    private LocalDate endDate;

    private List<PositionSlot> positionSlots;
    private List<String> skills;

    private AuditInfo auditInfo;

    private Project(
            String projectGuid,
            String authorId,
            String authorName,
            String title,
            String description,
            int recruitCount,
            int likes,
            RecruitmentType recruitmentType,
            ProjectStatus projectStatus,
            ProjectProgressType projectProgressType,
            LocalDate startDate,
            LocalDate endDate,
            List<PositionSlot> positionSlot,
            List<String> skills,
            AuditInfo auditInfo
    ) {
        this.projectGuid = projectGuid;
        this.authorId = authorId;
        this.authorName = authorName;

        if (title == null || title.isBlank())
            throw new IllegalArgumentException("title은 비어 있을 수 없습니다.");
        if (recruitCount < 0)
            throw new IllegalArgumentException("recruitCount는 0보다 작을 수 없습니다.");

        this.title = title;
        this.description = description;
        this.recruitCount = recruitCount;
        this.likes = likes;
        this.recruitmentType = Objects.requireNonNull(recruitmentType);
        this.projectStatus = Objects.requireNonNull(projectStatus);
        this.projectProgressType = Objects.requireNonNull(projectProgressType);
        this.startDate = Objects.requireNonNull(startDate);
        this.endDate = Objects.requireNonNull(endDate);
        this.positionSlots = positionSlot != null ? new ArrayList<>(positionSlot) : new ArrayList<>();
        this.skills = skills != null ? List.copyOf(skills) : Collections.emptyList();
        this.auditInfo = auditInfo != null ? auditInfo : AuditInfo.empty();
    }

    public static Project createNew(
            String userId,
            String username,
            String title,
            String content,
            int recruitCount,
            RecruitmentType recruitmentType,
            ProjectProgressType projectProgressType,
            LocalDate startDate,
            LocalDate endDate,
            List<PositionSlot> positionSlots,
            List<String> skills
    ) {
        return new Project(
                null,
                userId,
                username,
                title,
                content,
                recruitCount,
                0,
                recruitmentType,
                ProjectStatus.RECRUITING,
                projectProgressType,
                startDate,
                endDate,
                positionSlots,
                skills,
                AuditInfo.empty()
        );
    }

    public static Project reconstruct(
            String projectGuid,
            String userId,
            String username,
            String title,
            String content,
            RecruitmentType recruitmentType,
            ProjectStatus status,
            ProjectProgressType projectProgressType,
            int recruitCount,
            int likes,
            LocalDate startDate,
            LocalDate endDate,
            List<PositionSlot> positions,
            List<String> skills,
            AuditInfo auditInfo
    ) {
        return new Project(
                projectGuid,
                userId,
                username,
                title,
                content,
                recruitCount,
                likes,
                recruitmentType,
                status,
                projectProgressType,
                startDate,
                endDate,
                positions,
                skills,
                auditInfo
        );
    }

    public boolean isClosed() {
        return projectStatus == ProjectStatus.CLOSED || LocalDate.now().isAfter(endDate);
    }

    public void changeTitle(String newTitle) {
        if (newTitle == null || newTitle.isBlank()) {
            throw new IllegalArgumentException("title은 비어 있을 수 없습니다.");
        }
        this.title = newTitle;
    }

    public void changeRecruitmentType(RecruitmentType newType) {
        this.recruitmentType = Objects.requireNonNull(newType);
    }

    public void increaseLikes() {
        this.likes++;
    }

    private List<String> mergeList(List<String> original, String newItem) {
        List<String> newList = new ArrayList<>(original);
        newList.add(newItem);
        return List.copyOf(newList);
    }

    public void acceptUser(String userId, String position, String proficiency) {
        PositionSlot slot = positionSlots.stream()
                .filter(p -> p.getPosition().equals(position) && p.getProficiency().equals(proficiency))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("해당 포지션-숙련도 모집이 없습니다."));

        slot.acceptUser(userId); // 내부에서 인원 초과 체크
    }

    public boolean canApply(String position, String proficiency) {
        return positionSlots.stream()
                .filter(p -> p.getPosition().equals(position) && p.getProficiency().equals(proficiency))
                .anyMatch(p -> !p.isFull());
    }

    public String getProjectGuid() { return projectGuid; }
    public String getAuthorId() { return authorId; }
    public String getAuthorName() { return authorName; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public RecruitmentType getRecruitmentType() { return recruitmentType; }
    public ProjectStatus getProjectStatus() { return projectStatus; }
    public ProjectProgressType getDeliveryType() { return projectProgressType; }
    public int getRecruitCount() { return recruitCount; }
    public int getLikes() { return likes; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public List<PositionSlot> getPositionSlots() {return List.copyOf(positionSlots); }
    public List<String> getSkills() { return skills; }
    public AuditInfo getAuditInfo() { return auditInfo; }
}