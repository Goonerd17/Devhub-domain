package goonerd.devhub.adapters.in.project.command;

import goonerd.devhub.adapters.in.project.dto.CreateProjectPositionRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateProjectPositionCommand {

    private String positionName;
    private int capacity;
    private String level;

    public static CreateProjectPositionCommand fromCreateProjectRequestDto(CreateProjectPositionRequestDto createProjectPositionRequestDto) {
        return new CreateProjectPositionCommand(
                createProjectPositionRequestDto.getPositionName(),
                createProjectPositionRequestDto.getCapacity(),
                createProjectPositionRequestDto.getLevel()
        );
    }
}