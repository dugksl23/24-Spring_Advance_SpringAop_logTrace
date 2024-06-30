package hello.SpringAOP.internalCall.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CallLogAspect {

    @Pointcut("@annotation(hello.SpringAOP.exam.annotation.Trace)")
    public void tracePointCut() {}

    @Around("tracePointCut()")
    public Object doTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("CallLogAspect AOP = {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

}
