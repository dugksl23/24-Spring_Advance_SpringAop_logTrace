package hello.SpringAOP.pointcut;

import hello.SpringAOP.member.MemberService;
import hello.SpringAOP.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Slf4j
@Import({BeanTest.BeanAspectConfig.class})
// import 를 통해서 config 를 추가하는 것이며, 기본 config 를 통해 컴포넌트 스캔을 모든 객체에 수행한다.
public class BeanTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrderService orderService;

    @Test
    public void memberTest() {
        log.info("Proxy : {}", memberService.getClass());
        memberService.hello("hello");
    }

    @Test
    public void orderTest() {
        log.info("Proxy : {}", orderService.getClass());
        orderService.orderItem("hello");
    }


    @TestConfiguration
    static class BeanAspectConfig {

        @Bean
        public BeanAspect beanAspect() {
            return new BeanAspect();
        }

    }

    @Slf4j
    @Aspect
    static class BeanAspect {

        @Around("bean(*Service*) || bean(*Repository*)")
        public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
            //　메서드 실행 시점에 AOP를 적용하는 것이 아닌, 스프링 빈으로 등록된 대상을 찾아서 실행하는 것이다.
            log.info("[Bean]", joinPoint.getSignature().getName());
            return joinPoint.proceed();
        }

    }


}
