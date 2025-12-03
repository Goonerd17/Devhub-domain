package goonerd.devhub.guestbook.dto;

import goonerd.devhub.guestbook.entity.Project;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ProjectRequestDto {

    private String username;
    private String description;
    private int recruitCount;
    private int viewCount;
    private LocalDate startDate;
    private LocalDate endDate;

    public Project toEntity() {
        return Project.builder()
                .username(this.username)
                .description(this.description)
                .recruitCount(this.recruitCount)
                .viewCount(this.viewCount)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .build();
    }
}
