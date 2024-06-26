package hello.SpringAOP.pointcut;

import hello.SpringAOP.member.MemberService;
import hello.SpringAOP.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    @DisplayName("선언 타입 매칭 : 정확히 선언 타입 경로")
    void withinExact() {
        pointcut.setExpression("within(hello.SpringAOP.member.MemberServiceImpl)");
        assertThat(pointcut.matches(helloMethod,
                MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("선언 타입 매칭 : * 패턴 매칭")
    void withinStar() {
        pointcut.setExpression("within(hello.SpringAOP.member.*Service*)");
        assertThat(pointcut.matches(helloMethod,
                MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("선언 타입 매칭 : 본인 패키지 포함 그 하위 패키지 전부 포함")
    void withinSubPackage() {
        pointcut.setExpression("within(hello.SpringAOP..*)");
        assertThat(pointcut.matches(helloMethod,
                MemberServiceImpl.class)).isTrue();
    }

    @Test
    @DisplayName("Within 선언 타입 매칭 : 부모 타입 허용 안함")
    void withinSuperType_false() {
        pointcut.setExpression("within(hello.SpringAOP..MemberService)");
        assertThat(pointcut.matches(helloMethod,
                MemberServiceImpl.class)).isFalse();
    }

    @Test
    @DisplayName("execution 선언 타입 매칭 : 타입 기반이기에 부모 타입 허용")
    void executionSuperType_true() {
        pointcut.setExpression("execution(* hello.SpringAOP..MemberService.*(..))");
        assertThat(pointcut.matches(helloMethod,
                MemberService.class)).isTrue();
    }

}
