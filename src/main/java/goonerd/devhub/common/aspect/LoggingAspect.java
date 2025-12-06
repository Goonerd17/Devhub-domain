package goonerd.devhub.common.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.tracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    private final ObjectMapper objectMapper;
    private final Tracer tracer;

    public LoggingAspect(ObjectMapper objectMapper, Tracer tracer) {
        this.objectMapper = objectMapper;
        this.tracer = tracer;
    }

    @Around("execution(* goonerd.devhub..controller..*(..)) || " +
            "execution(* goonerd.devhub..service..*(..)) || " +
            "execution(* goonerd.devhub..repository..*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String params = getParamsAsJson(joinPoint.getArgs());

        log.info("START {}.{}() with params: {}", className, methodName, params);

        try {
            Object result = joinPoint.proceed();
            long elapsed = System.currentTimeMillis() - start;
            String resultLog = summarizeResult(result);

            log.info("END {}.{}() in {} ms with result: {}", className, methodName, elapsed, resultLog);
            return result;
        } catch (Throwable ex) {
            long elapsed = System.currentTimeMillis() - start;
            log.error("EXCEPTION in {}.{}() after {} ms, params: {}, error: {}",
                    className, methodName, elapsed, params, ex.getMessage(), ex);
            throw ex;
        }
    }

    private String getParamsAsJson(Object[] args) {
        if (args == null || args.length == 0) return "[]";
        try {
            return objectMapper.writeValueAsString(args);
        } catch (JsonProcessingException e) {
            return Arrays.toString(args);
        }
    }

    private String summarizeResult(Object result) {
        if (result == null) return "null";

        if (result instanceof Collection<?> coll) {
            int size = coll.size();
            int limit = 5;
            if (size > limit) {
                try {
                    String sample = objectMapper.writeValueAsString(coll.stream().limit(limit).collect(Collectors.toList()));
                    return String.format("%s... (total %d items)", sample, size);
                } catch (JsonProcessingException e) {
                    return String.format("[Collection of %d items]", size);
                }
            } else {
                try {
                    return objectMapper.writeValueAsString(coll);
                } catch (JsonProcessingException e) {
                    return coll.toString();
                }
            }
        }

        try {
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            return result.toString();
        }
    }
}
