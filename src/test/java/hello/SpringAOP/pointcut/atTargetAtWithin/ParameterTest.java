package hello.SpringAOP.pointcut.atTargetAtWithin;


import hello.SpringAOP.member.MemberService;
import hello.SpringAOP.member.annotation.ClassAop;
import hello.SpringAOP.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

@Slf4j
@SpringBootTest
public class ParameterTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void argsTest() {

        log.info("memberService proxy = {}", memberService.getClass());
        memberService.hello("helloArgs");

    }


    @TestConfiguration
    static class ParameterTestConfiguration {
        @Bean
        public ParameterAspect parameterAspect() {
            return new ParameterAspect();
        }
    }

    @Aspect
    static class ParameterAspect {

        @Pointcut("execution(* hello.SpringAOP.member.*.*hello(..))")
        public void allMemberPointcut() {
        }


        @Around("allMemberPointcut()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("Pointcut signature = {}", joinPoint.getSignature());
            Object[] args = joinPoint.getArgs();
            Arrays.stream(args).forEach(arg -> {

                log.info("[logArgs1] arg type = {}", arg.getClass().getSimpleName());
                log.info("[logArgs1] arg value = {}", arg);
            });

            return joinPoint.proceed();
        }

        @Around("allMemberPointcut() && args(param1, ..)")
        public Object logArgs2(ProceedingJoinPoint joinPoint, String param1) throws Throwable {
            log.info("Pointcut signature = {}", joinPoint.getSignature());
            log.info("[logArgs2] param1 type = {}", param1.getClass());
            log.info("[logArgs2] param1 value = {}", param1);
            return joinPoint.proceed();
        }


        @Before("allMemberPointcut() && args(arg, ..)")
        public void logArgs3(String arg) throws Throwable {
            log.info("[logArgs3] value = {}", arg);
        }

        @Before("allMemberPointcut() && this(obj)")
        // this : Proxy 객체 출력
        public void thisArgs4(JoinPoint joinPoint, MemberService obj) throws Throwable {
            log.info("[thisArgs] this = {}", obj.getClass());
            log.info("[thisArgs] Signature = {}", joinPoint.getSignature());
        }

        // target 은 실행 객체에 선언된 어노테이션을 대상으로 하는 조인 포인트
        @Before("allMemberPointcut() && target(obj)")
        // target : 실제 object　출력
        public void targetArgs(JoinPoint joinPoint, MemberService obj) throws Throwable {
            log.info("[targetArgs] target = {}", obj.getClass());
            log.info("[targetArgs] Signature = {}", joinPoint.getSignature());
        }

        // ==== @ 어노테이션을 대상으로 하는 조인포인트 지시자들

        // target 은 실행 객체에 선언된 어노테이션을 대상으로 하는 조인 포인트
        @Before("allMemberPointcut() && @target(annotation)")
        // target : 실제 object　출력
        public void atTarget(JoinPoint joinPoint, ClassAop annotation) throws Throwable {
            log.info("[@targetArgs1] = {}", annotation.getClass());
            log.info("[@targetArgs1] Signature = {}", joinPoint.getSignature());
        }

        // target 은 실행 객체에 선언된 어노테이션을 대상으로 하는 조인 포인트
        @Before("allMemberPointcut() && @within(annotation)")
        // target : 실제 object　출력
        public void atWithin(JoinPoint joinPoint, ClassAop annotation) throws Throwable {
            log.info("[@withinArgs] = {}", annotation.getClass());
            log.info("[@withinArgs] Signature = {}", joinPoint.getSignature());
        }

        // target 은 실행 객체 메서드에 선언된 어노테이션을 대상으로 하는 조인 포인트
        @Before("allMemberPointcut() && @annotation(annotation)")
        // target : 실제 object　출력
        public void atAnnotation(JoinPoint joinPoint, MethodAop annotation) throws Throwable {
            log.info("[@annotationArgs] = {}", annotation.getClass());
            log.info("[@annotationArgs] annotation value = {}", annotation.value());
            log.info("[@annotationArgs] Signature = {}", joinPoint.getSignature());
        }

    }


}
