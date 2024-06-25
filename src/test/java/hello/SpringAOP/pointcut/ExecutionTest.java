package hello.SpringAOP.pointcut;

import hello.SpringAOP.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.InitBinder;

import java.lang.reflect.Method;


@Slf4j
@SpringBootTest
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        // 파라미터를 전달.
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);

    }

    @Test
    void printMethod() {
        log.info("helloMethod = {}", helloMethod);
        // @Return public java.lang.String hello.SpringAOP.member.MemberServiceImpl.hello(java.lang.String)
        // 반환값은 execution(*..hello.aop.order..*..(..)) 처럼 내부에 작성할 포인트컷 시그니처를 반환한다.
        // 포인트컷 시그니처 : public java.lang.String hello.SpringAOP.member.MemberServiceImpl.hello(java.lang.String)
    }
}
