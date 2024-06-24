package hello.SpringAOP.order.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
//@Component
@Slf4j
public class AspectV1 {


    // 자체적으로 Advisor 변환되어, 어드바이저를 추가한다.
    @Around("execution(* hello.SpringAOP.order..*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        log.info("[logTrace start] {}", signature);
        Object proceed = joinPoint.proceed();
        log.info("[logTrace end] {}", signature);

        return proceed;
    }

//    @Around("execution(* hello.SpringAOP.order..*(..))")
//    public Object around2(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        log.info("[Transaction start] {}", "transaction");
//        Object proceed = joinPoint.proceed();
//        log.info("[Transaction end]");
//        return proceed;
//    }

}
