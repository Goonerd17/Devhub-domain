package goonerd.devhub.domain.project;

import goonerd.devhub.domain.common.AuditInfo;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Project {

    private String projectGuid;
    private String userId;
    private String username;
    private String title;
    private String content;
    private RecruitmentType recruitmentType;
    private ProjectStatus status;
    private DeliveryType deliveryType;
    private int recruitCount;
    private int likes;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> positions;
    private List<String> skills;
    private List<String> acceptedUserIds;

    private final AuditInfo auditInfo;

    private Project(
            String projectGuid,
            String userId,
            String username,
            String title,
            String content,
            RecruitmentType recruitmentType,
            ProjectStatus status,
            DeliveryType deliveryType,
            int recruitCount,
            int likes,
            LocalDate startDate,
            LocalDate endDate,
            List<String> positions,
            List<String> skills,
            List<String> acceptedUserIds,
            AuditInfo auditInfo
    ) {
        this.projectGuid = projectGuid;
        this.userId = userId;
        this.username = username;

        if (title == null || title.isBlank())
            throw new IllegalArgumentException("title은 비어 있을 수 없습니다.");
        if (recruitCount < 0)
            throw new IllegalArgumentException("recruitCount는 0보다 작을 수 없습니다.");

        this.title = title;
        this.content = content;
        this.recruitmentType = Objects.requireNonNull(recruitmentType);
        this.status = Objects.requireNonNull(status);
        this.deliveryType = Objects.requireNonNull(deliveryType);
        this.recruitCount = recruitCount;
        this.likes = likes;
        this.startDate = Objects.requireNonNull(startDate);
        this.endDate = Objects.requireNonNull(endDate);

        this.positions = positions != null ? List.copyOf(positions) : Collections.emptyList();
        this.skills = skills != null ? List.copyOf(skills) : Collections.emptyList();
        this.acceptedUserIds = acceptedUserIds != null ? List.copyOf(acceptedUserIds) : Collections.emptyList();

        this.auditInfo = auditInfo != null ? auditInfo : AuditInfo.empty();
    }


    // --- 신규 프로젝트 생성 ---
    public static Project createNew(
            String userId,
            String username,
            String title,
            String content,
            RecruitmentType recruitmentType,
            DeliveryType deliveryType,
            int recruitCount,
            LocalDate startDate,
            LocalDate endDate,
            List<String> positions,
            List<String> skills
    ) {
        return new Project(
                null,
                userId,
                username,
                title,
                content,
                recruitmentType,
                ProjectStatus.RECRUITING,
                deliveryType,
                recruitCount,
                0,
                startDate,
                endDate,
                positions,
                skills,
                Collections.emptyList(),
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
            DeliveryType deliveryType,
            int recruitCount,
            int likes,
            LocalDate startDate,
            LocalDate endDate,
            List<String> positions,
            List<String> skills,
            List<String> acceptedUserIds,
            AuditInfo auditInfo
    ) {
        return new Project(
                projectGuid,
                userId,
                username,
                title,
                content,
                recruitmentType,
                status,
                deliveryType,
                recruitCount,
                likes,
                startDate,
                endDate,
                positions,
                skills,
                acceptedUserIds,
                auditInfo
        );
    }

    // --- 도메인 행동들 ---
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

    public void acceptUser(String userId) {
        if (acceptedUserIds.size() >= recruitCount) {
            throw new IllegalStateException("모집 인원을 초과할 수 없습니다.");
        }
        this.acceptedUserIds = mergeList(acceptedUserIds, userId);
    }

    private List<String> mergeList(List<String> original, String newItem) {
        List<String> newList = new java.util.ArrayList<>(original);
        newList.add(newItem);
        return List.copyOf(newList);
    }

    // --- getters ---
    public String getProjectGuid() { return projectGuid; }
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public RecruitmentType getRecruitmentType() { return recruitmentType; }
    public ProjectStatus getStatus() { return status; }
    public DeliveryType getDeliveryType() { return deliveryType; }
    public int getRecruitCount() { return recruitCount; }
    public int getLikes() { return likes; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public List<String> getPositions() { return positions; }
    public List<String> getSkills() { return skills; }
    public List<String> getAcceptedUserIds() { return acceptedUserIds; }
    public AuditInfo getAuditInfo() { return auditInfo; }
}
