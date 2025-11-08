package goonerd.devhub.guestbook.dto;

import goonerd.devhub.guestbook.entity.GuestBook;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestBookRequestDto {

    private String username;
    private String description;

    public GuestBook toEntity() {
        return GuestBook.builder()
                .username(this.username)
                .description(this.description)
                .build();
    }
}
