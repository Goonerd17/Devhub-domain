package goonerd.devhub.guestbook.controller;

import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.guestbook.dto.GuestBookRequestDto;
import goonerd.devhub.guestbook.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/guestbook")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://devhub.local")
@Slf4j
public class GuestBookController {

    private final GuestBookService guestBookService;

    @GetMapping()
    public ResponseEntity<ApiResponseVo<?>> listGuestBooks() {
        log.info("listGuestBooks, ArgoCD test! Sync Policy is Manual test v6");
        return guestBookService.listGuestBook();
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseVo<?>> createGuestBook(@RequestBody GuestBookRequestDto guestBookRequestDto) {
        return guestBookService.createGuestBook(guestBookRequestDto);
    }
}