package hello.SpringAOP.order.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
//@Component
@Slf4j
public class AspectV2 {


    /**
     * PointCut Signature
     * hello.aop.order 패키지를 포함한 그 하위의 모든 패키지를 대상으로 한다.
     */
    @Pointcut("execution(* hello.SpringAOP.order..*(..))")
    public void allOrderDomain() {
    }

    // 자체적으로 Advisor 변환되어, 어드바이저를 추가한다.
    @Around("allOrderDomain()")
    public Object logTraceV2(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        log.info("[logTrace start] {}", signature);
        Object proceed = joinPoint.proceed();
        log.info("[logTrace end] {}", signature);

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
