package goonerd.devhub.guestbook.dto;

import goonerd.devhub.guestbook.entity.GuestBook;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GuestBookResponseDto {

    private Long id;
    private String username;
    private String description;

    public static GuestBookResponseDto fromEntity(GuestBook guestBook) {
        return GuestBookResponseDto.builder()
                .id(guestBook.getId())
                .username(guestBook.getUsername())
                .description(guestBook.getDescription())
                .build();
    }
}