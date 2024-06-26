package hello.SpringAOP.pointcut.atTargetAtWithin;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class AtTargetAtWithinAspect {

    // @target : 인스턴스 기준으로 해당 타입(부모, 자신 포함)의 모든 메서드를 조인 포인트로 선정
    @Around("execution(* hello.SpringAOP..*(..)) && @target(hello.SpringAOP.member.annotation.ClassAop)")
    public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[@target] {}", joinPoint.getSignature());
        Object proceed = joinPoint.proceed();
        return proceed;
    }

    // @within : 인스턴스 기준으로 본인 타입의 내부 메서드만 조인 포인트로 선정
    @Around("execution(* hello.SpringAOP..*(..)) && @within(hello.SpringAOP.member.annotation.ClassAop)")
    public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[@within] {}", joinPoint.getSignature());
        Object proceed = joinPoint.proceed();
        return proceed;
    }

}
