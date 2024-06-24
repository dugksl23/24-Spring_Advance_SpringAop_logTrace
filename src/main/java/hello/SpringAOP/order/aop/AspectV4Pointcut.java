package hello.SpringAOP.order.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class AspectV4Pointcut {


    // 자체적으로 Advisor 변환되어, 어드바이저를 추가한다.
    @Around("hello.SpringAOP.order.aop.pointcut.Pointcuts.allService()")
    public Object transaction(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        Object result = null;
        try {
            log.info("[Transaction start] {}", signature);
            result = joinPoint.proceed();
            log.info("[Transaction commit] {}", signature);
        } catch (Exception e) {
            log.error("[Transaction rollback] {}, {}", signature, e.getMessage());
            throw e;
        } finally {
            log.info("[Transaction end] {}", signature);
        }

        return result;
    }

    // hello.app.order 패키지와 하위 패키지를 포함하면서 적용 패턴이 *Service 에 적용
    @Around("hello.SpringAOP.order.aop.pointcut.Pointcuts.allOrderAndAllService()")
    public Object logTraceV2(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        log.info("[LogTrace start] {}", signature);
        Object proceed = joinPoint.proceed();

        return proceed;
    }


    //    @Around("execution(* hello.SpringAOP.order..*(..))")
    public Object logTraceV1(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        log.info("[logTrace start] {}", signature);
        Object proceed = joinPoint.proceed();
        log.info("[logTrace end] {}", signature);

        return proceed;
    }

}
