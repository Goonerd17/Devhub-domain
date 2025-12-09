package goonerd.devhub.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestVo {

    private int page = 0;
    private int size = 10;

    public Pageable toPageable() {
        return PageRequest.of(page, size);
    }
}