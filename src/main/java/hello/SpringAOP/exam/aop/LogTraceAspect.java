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
//@Order(1)
public class LogTraceAspect {

    @Pointcut("@annotation(hello.SpringAOP.exam.annotation.Trace)")
    public void tracePointCut() {}


    @Around("tracePointCut() && args(arg)")
    public Object logTrace(ProceedingJoinPoint joinPoint, Object arg) throws Throwable {


        Signature signature = joinPoint.getSignature();
        log.info("[LogTrace] JoinPoint : {}", signature);
        Object result = joinPoint.proceed();

        return result;
    }






}
