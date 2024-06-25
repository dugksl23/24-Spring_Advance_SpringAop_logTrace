package hello.SpringAOP.pointcut;

import hello.SpringAOP.member.MemberService;
import hello.SpringAOP.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;


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
        pointcut.setExpression("execution(* helloService.*(..))");
        // @Return public java.lang.String hello.SpringAOP.member.MemberServiceImpl.hello(java.lang.String)
        // 반환값은 execution(*..hello.aop.order..*..(..)) 처럼 내부에 작성할 포인트컷 시그니처를 반환한다.
        // 포인트컷 시그니처 : public java.lang.String hello.SpringAOP.member.MemberServiceImpl.hello(java.lang.String)
    }

    @Test
    @DisplayName("포인트컷 실행 지점 매우 상세히 Match")
    void exactMatchPointcut() {
        // 예외 생략
        String pointcutSignature = "execution(public String hello.SpringAOP.member.MemberService.hello(String))";
        pointcut.setExpression(pointcutSignature);
        // assertJ 쓰는 것을 권장한다.
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }


    @Test
    @DisplayName("포인트컷 실행 조건 모두 Match")
    void allMatchPointcut() {
        // 예외 생략
        String pointcutSignature = "execution(* * (..))";
        pointcut.setExpression(pointcutSignature);
        // assertJ 쓰는 것을 권장한다.
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }

    @Test
    @DisplayName("포인트컷 실행 조건 Method Name 매치")
    void methodNameMatchPointcut() {
        // 예외 생략
        String pointcutSignature = "execution(* hello.SpringAOP.member.MemberServiceImpl.*(..))";
        pointcut.setExpression(pointcutSignature);
        // assertJ 쓰는 것을 권장한다.
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }

    @Test
    @DisplayName("포인트컷 실행 조건 선언 타입 매치")
    void classMatchPointcut() {
        // 예외 생략
        String pointcutSignature = "execution(* MemberService.hello(..))";
        pointcut.setExpression(pointcutSignature);
        // assertJ 쓰는 것을 권장한다.
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }

    @Test
    @DisplayName("포인트컷 실행 조건 Pattern Match v1")
    void patternMatchPointcut1() {
        // 예외 생략
        String pointcutSignature = "execution(* hel*(..))";
        pointcut.setExpression(pointcutSignature);
        // assertJ 쓰는 것을 권장한다.
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }

    @Test
    @DisplayName("포인트컷 실행 조건 Pattern Match v2")
    void patternMatchPointcut2() {
        // 예외 생략
        String pointcutSignature = "execution(* *lo(..))";
        pointcut.setExpression(pointcutSignature);
        // assertJ 쓰는 것을 권장한다.
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }

    @Test
    @DisplayName("포인트컷 실행 조건 fail")
    void nameMatchPointcut_fail() {
        // 예외 생략
        String pointcutSignature = "execution(* hello(Integer))";
        pointcut.setExpression(pointcutSignature);
        // assertJ 쓰는 것을 권장한다.
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isFalse();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }

    @Test
    @DisplayName("포인트컷 실행 조건 package name exact match")
    void packageNameExactMatchPointcutV1() {
        // 예외 생략
        String pointcutSignature = "execution(* hello.SpringAOP.member.MemberServiceImpl.hello(String))";
        pointcut.setExpression(pointcutSignature);
        // assertJ 쓰는 것을 권장한다.
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }

    @Test
    @DisplayName("포인트컷 실행 조건 package name simple match")
    void packageNameMatchPointcutV2() {
        // 예외 생략
        String pointcutSignature = "execution(* hello.SpringAOP.member.*.*(**))";
        pointcut.setExpression(pointcutSignature);
        // assertJ 쓰는 것을 권장한다.
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }

    @Test
    @DisplayName("포인트컷 실행 조건 package name match fail")
    void packageNameMatchPointcut_fail() {
        // 예외 생략
        String pointcutSignature = "execution(* hello.*.*.*.*(..))";
        // 접근 제어자, 반환타입, 선언 타입, 파라미터, 예외 순서대로 생략이 가능하다.
        // 생략은 * 을 통해 생략이 가능하다.
        pointcut.setExpression(pointcutSignature);
        // assertJ 쓰는 것을 권장한다.
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }

    @Test
    @DisplayName("포인트컷 실행 조건 / 현재 패키지와 그 하위 패키지 모두 포함(클래스와 메서드까지)")
    void packageNameMatchPointcut_subPackageV1() {
        // 예외 생략
        String pointcutSignature = "execution(* hello..*(..))";
        // 접근 제어자, 반환타입, 선언 타입, 파라미터, 예외 순서대로 생략이 가능하다.
        // 생략은 * 을 통해 생략이 가능하다.
        // hello.. hello 현재 패키를 포함한 그 하위 패키지 전부라는 뜻이다.
        // *(..) 포인트컷 적용 시점이 메서드 실행 시점이기에 메서드는 생략 불가하며, 반드시 포함해야 한다.
        pointcut.setExpression(pointcutSignature);
        // assertJ 쓰는 것을 권장한다.
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }

    @Test
    @DisplayName("포인트컷 실행 조건 / 현재 패키지와 그 하위 패키지 모두 포함(클래스와 메서드까지)")
    void packageNameMatchPointcut_subPackageV2() {
        // 예외 생략
        String pointcutSignature = "execution(* hello.SpringAOP..*.*(..))";
        // 접근 제어자, 반환타입, 선언 타입, 파라미터, 예외 순서대로 생략이 가능하다.
        // 생략은 * 을 통해 생략이 가능하다.
        // hello.. hello 현재 패키를 포함한 그 하위 패키지 전부라는 뜻이다.
        // *(..) 포인트컷 적용 시점이 메서드 실행 시점이기에 메서드는 생략 불가하며, 반드시 포함해야 한다.
        pointcut.setExpression(pointcutSignature);
        // assertJ 쓰는 것을 권장한다.
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }

}
