package goonerd.devhub.guestbook.controller;

import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.guestbook.dto.ProjectRequestDto;
import goonerd.devhub.guestbook.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guestbook")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://devhub.local")
@Slf4j
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping()
    public ResponseEntity<ApiResponseVo<?>> listGuestBooks() {
        log.info("This is the list of guest books. testDate : 2025-12-01");
        return projectService.listGuestBook();
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseVo<?>> createGuestBook(@RequestBody ProjectRequestDto projectRequestDto) {
        return projectService.createGuestBook(projectRequestDto);
    }
}