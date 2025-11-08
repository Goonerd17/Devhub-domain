package goonerd.devhub.guestbook.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestBookRequestDto {

    private String username;
    private String description;
}
