package hello.SpringAOP.order.aop.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
@Aspect
@Order(1)
public class TransactionAspect {

    @Around("hello.SpringAOP.order.aop.pointcut.Pointcuts.allOrderAndAllService()")
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

}
