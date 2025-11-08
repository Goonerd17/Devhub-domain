package goonerd.devhub.guestbook.service;

import goonerd.devhub.common.devhubenum.ErrorCodeEnum;
import goonerd.devhub.common.devhubenum.SuccessCodeEnum;
import goonerd.devhub.common.utils.ApiResponseBuilder;
import goonerd.devhub.common.vo.ApiResponseVo;
import goonerd.devhub.guestbook.dto.GuestBookRequestDto;
import goonerd.devhub.guestbook.dto.GuestBookResponseDto;
import goonerd.devhub.guestbook.entity.GuestBook;
import goonerd.devhub.guestbook.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuestBookService {

    private final GuestBookRepository guestBookRepository;
    private final ApiResponseBuilder apiResponseBuilder;

    public ResponseEntity<ApiResponseVo<?>> listGuestBook() {
        try {
            List<GuestBookResponseDto> guestBookResponseList = guestBookRepository.findAll().stream()
                    .map(GuestBookResponseDto::fromEntity)
                    .toList();
            return apiResponseBuilder.success(SuccessCodeEnum.GUESTBOOK_LIST_SUCCESS, Collections.emptyMap(), guestBookResponseList);
        } catch (Exception e) {
            return apiResponseBuilder.fail(ErrorCodeEnum.GUESTBOOK_LIST_FAIL, Collections.emptyMap(), e.getMessage());
        } finally {
            log.info("GuestBookService - listGuestBook is finished");
        }
    }

    public ResponseEntity<ApiResponseVo<?>> createGuestBook(GuestBookRequestDto guestBookRequestDto) {
        try {
            GuestBook guestBookEntity = guestBookRepository.save(guestBookRequestDto.toEntity());
            GuestBookResponseDto guestBookResponseDto = GuestBookResponseDto.fromEntity(guestBookEntity);
            return apiResponseBuilder.success(SuccessCodeEnum.GUESTBOOK_CREATE_SUCCESS, guestBookRequestDto, guestBookResponseDto);
        } catch (Exception e) {
            return apiResponseBuilder.fail(ErrorCodeEnum.GUESTBOOK_CREATE_FAIL, guestBookRequestDto, e.getMessage());
        } finally {
            log.info("param 1 {}, param 2 {} : ", guestBookRequestDto.getUsername(), guestBookRequestDto.getDescription());
            log.info("GuestBookService - createGuestBook is finished");
        }
    }
}
