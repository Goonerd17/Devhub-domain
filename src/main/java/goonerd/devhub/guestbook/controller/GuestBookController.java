package goonerd.devhub.guestbook.controller;

import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.guestbook.dto.GuestBookRequestDto;
import goonerd.devhub.guestbook.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/guestbook")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://devhub.local")
public class GuestBookController {

    private final GuestBookService guestBookService;

    @GetMapping()
    public ResponseEntity<ApiResponseVo<?>> listGuestBooks() {
        return guestBookService.listGuestBook();
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseVo<?>> createGuestBook(@RequestBody GuestBookRequestDto guestBookRequestDto) {
        return guestBookService.createGuestBook(guestBookRequestDto);
    }
}