package hello.SpringAOP.exam.aop;

import hello.SpringAOP.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
//@Order(2)
public class RetryLogTraceAspect {

    @Pointcut("execution(* hello.SpringAOP.exam.. *(..))")
    public void allExamPointCut() {
    }

    @Pointcut("@annotation(hello.SpringAOP.exam.annotation.Retry)")
    public void retryAnnotationPointCut() {
    }

    @Around("@annotation(retry)")
    public Object retry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {

        log.info("[RetryLogTrace] Join Point {},", joinPoint.getSignature());
        int maxTryValue = retry.value();
        Object result = null;
        Exception exceptionHolder = null;

        for (int i = 1; i < maxTryValue; i++) {
            try {
                // tryAspect - > retryAspect -> target.proceed() : 요청 1 ~ 4 회 성공
                // tryAspect - > retryAspect -> target.proceed() : 5회 throw exception
                // retryAspect retry for 문 -> try count 2 회차 성공
                log.info("try count = {}, maxTryValue = {}", i, maxTryValue);
                return result = joinPoint.proceed();
            } catch (Exception e) {
                log.info("try count = {}, Ex = {}", i, e.getMessage());
                exceptionHolder = e;
            }

        }

        throw exceptionHolder;
    }


}
