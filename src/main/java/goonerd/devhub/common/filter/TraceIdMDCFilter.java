package goonerd.devhub.common.filter;

import brave.Span;
import brave.Tracer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class TraceIdMDCFilter extends OncePerRequestFilter {

    private final Tracer tracer;
    private static final String TRACE_ID_KEY = "traceId";

    public TraceIdMDCFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // UUID 기반 10자리 traceId 생성
            String traceId = UUID.randomUUID().toString().replace("-", "").substring(0, 10);

            // MDC에 강제로 넣음
            MDC.put(TRACE_ID_KEY, traceId);

            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(TRACE_ID_KEY);
        }
    }
}
