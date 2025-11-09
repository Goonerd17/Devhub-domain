package goonerd.devhub.guestbook.dto;

import goonerd.devhub.guestbook.entity.GuestBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class GuestBookRequestDto {

    @NonNull
    private String username;
    @NonNull
    private String description;

    public GuestBook toEntity() {
        return GuestBook.builder()
                .username(this.username)
                .description(this.description)
                .build();
    }
}
