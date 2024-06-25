package hello.SpringAOP.order.aop;


import hello.SpringAOP.order.aop.aspects.LogTraceAspect;
import hello.SpringAOP.order.aop.aspects.TransactionAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Import;

@Slf4j
@Aspect
public class AspectV6Advice {

    @Around("hello.SpringAOP.order.aop.pointcut.Pointcuts.allOrderAndAllService()")
    public Object transaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            // @Before
            log.info("[Transaction start] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            // @AfterReturning
            log.info("[Transaction commit] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            // @AfterThrowing
            log.error("[Transaction rollback] {}, {}", joinPoint.getSignature(), e.getMessage());
            throw e;
        } finally {
            // @After
            log.info("[Transaction end] {}", joinPoint.getSignature());
        }

    }

    @Before("hello.SpringAOP.order.aop.pointcut.Pointcuts.allOrderAndAllService()")
    public void before(JoinPoint joinPoint) {
        log.info("[Before] [Transaction start] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "hello.SpringAOP.order.aop.pointcut.Pointcuts.allOrderAndAllService()", returning = "result")
    public Object afterReturning(JoinPoint joinPoint, Object result) {
        log.info("[AfterReturning] [return = {}]", result);
        log.info("[AfterReturning] [Transaction commit] {}", joinPoint.getSignature());
        return result;

        // @Around 와 같은 경우에는 반환 값을 조작할 수 있다.
        // 하지만, @AfterReturning 은 반환값을 조작할 수 없다.
    }


    @AfterThrowing(value = "hello.SpringAOP.order.aop.pointcut.Pointcuts.allOrderAndAllService()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[AfterThrowing] [ex] {}", ex.getMessage());
        log.info("[AfterThrowing] [Transaction rollback] {}", joinPoint.getSignature());
        ex.printStackTrace();
    }

    @After("hello.SpringAOP.order.aop.pointcut.Pointcuts.allOrderAndAllService()")
    public void after(JoinPoint joinPoint) {
        log.info("[After] [Transaction end] {}", joinPoint.getSignature());
    }


}
