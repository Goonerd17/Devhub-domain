package goonerd.devhub.common.converter;

import goonerd.devhub.common.vo.PageVo;
import org.springframework.data.domain.Page;

import java.util.function.Function;

public class PageConverter {

    public static <T, R> PageVo<R> convert(Page<T> page, Function<T, R> mapper) {
        return new PageVo<>(
                page.getContent().stream().map(mapper).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast()
        );
    }
}
