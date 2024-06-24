package hello.SpringAOP.order.aop.aspects;

import jdk.jfr.Category;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
//@Component
@Slf4j
@Order(2)
public class LogTraceAspect {

    @Around("hello.SpringAOP.order.aop.pointcut.Pointcuts.allOrderDomain()")
    public Object logTraceV2(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        log.info("[LogTrace start] {}", signature);
        Object proceed = joinPoint.proceed();
        log.info("[LogTrace end] {}", signature);

        return proceed;
    }

}
