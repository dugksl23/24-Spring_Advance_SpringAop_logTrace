package hello.SpringAOP.pointcut;


import hello.SpringAOP.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(AtAnnotationTest.AtAnnotationTestContextConfiguration.class)
public class AtAnnotationTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void success(){
        log.info("target Proxy : {}", memberService.getClass());
        log.info("Is proxy: {}", AopUtils.isAopProxy(memberService));
        log.info("Is JDK proxy: {}", AopUtils.isJdkDynamicProxy(memberService));
        log.info("Is CGLIB proxy: {}", AopUtils.isCglibProxy(memberService));
        memberService.hello("hello");
    }

    @Aspect
    @Slf4j
    static class AtAnnotationTestAspect {

        @Around("@annotation(hello.SpringAOP.member.annotation.MethodAop)")
        public Object methodAop(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@annotation] : {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            return result;
        }

//        @Around("@annotation(hello.SpringAOP.member.annotation.ClassAop)")
//        public Object classAop(ProceedingJoinPoint joinPoint) throws Throwable {
//            log.info("[@annotation] : {}", joinPoint.getSignature());
//            Object result = joinPoint.proceed();
//            return result;
//        }
    }

    @TestConfiguration
    static class AtAnnotationTestContextConfiguration {
        @Bean
        public AtAnnotationTestAspect atAnnotationTestAspect() {
            return new AtAnnotationTestAspect();
        }
    }
}
