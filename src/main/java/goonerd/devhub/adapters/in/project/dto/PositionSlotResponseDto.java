package goonerd.devhub.adapters.in.project.dto;

import goonerd.devhub.domain.project.PositionSlot;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "포지션 슬롯 정보")
public class PositionSlotResponseDto {

    private String position;
    private int capacity;
    private String level;
    private int approvedCount;
    private boolean full;

    public static PositionSlotResponseDto fromDomain(PositionSlot slot) {
        return PositionSlotResponseDto.builder()
                .position(slot.getPosition())
                .capacity(slot.getCapacity())
                .approvedCount(slot.getApprovedCount())
                .full(slot.isFull())
                .build();
    }
}
