package goonerd.devhub.adapters.in.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class SearchProjectRequestDto {

    private String username;

    private String keyword;

    private Integer minRecruitCount;
    private Integer maxRecruitCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDateTo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDateTo;
}
