package hello.SpringAOP.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogTraceAspect {

    @Pointcut("@annotation(hello.SpringAOP.exam.annotation.Trace)")
    public void tracePointCut() {}


    @Around("tracePointCut() && args(arg)")
    public Object logTrace(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        long endTime = System.currentTimeMillis();
        log.info("[LogTrace] end time : {} ms", endTime - startTime);
        log.info("[LogTrace] JoinPoint : {}", signature);
        log.info("[LogTrace] args : {}", arg);


        return result;
    }





}
