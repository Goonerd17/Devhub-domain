package goonerd.devhub.adapters.in.common.pagination;

import goonerd.devhub.adapters.in.common.vo.PageVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public class PageConverter {

    public static PageVo toPageVo(Page<?> page) {
        return PageVo.from(page);
    }

    public static <T, R> List<R> toList(Page<T> page, Function<T, R> mapper) {
        return page.getContent()
                .stream()
                .map(mapper)
                .toList();
    }
}