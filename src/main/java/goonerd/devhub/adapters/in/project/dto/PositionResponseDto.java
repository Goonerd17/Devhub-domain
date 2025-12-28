package goonerd.devhub.adapters.in.project.dto;

import goonerd.devhub.domains.position.Position;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "포지션 정보")
public class PositionResponseDto {

    private String positionName;
    private int capacity;
    private String level;
    private int approvedCount;
    private boolean full;

    public static PositionResponseDto fromDomain(Position position) {
        return PositionResponseDto.builder()
                .positionName(position.getPositionName())
                .capacity(position.getCapacity())
                .level(position.getLevel())
                .approvedCount(position.getApprovedCount())
                .full(position.isFull())
                .build();
    }
}
