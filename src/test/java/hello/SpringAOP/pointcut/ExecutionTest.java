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
    Method internalMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        // 파라미터를 전달.
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
        internalMethod = MemberServiceImpl.class.getMethod("internalMethod", String.class);

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
        String pointcutSignature = "execution(* hello(String))";
        pointcut.setExpression(pointcutSignature);
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
        String pointcutSignature = "execution(* hello..*(..))";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }

    @Test
    @DisplayName("포인트컷 실행 조건 / 현재 패키지와 그 하위 패키지 모두 포함(클래스와 메서드까지)")
    void packageNameMatchPointcut_subPackageV2() {
        String pointcutSignature = "execution(* hello.SpringAOP..*.*(..))";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }

    @Test
    @DisplayName("타입 매칭 : 부모 타입 허용")
    void typeMatchSuperTypePointcut() {
        String pointcutSignature = "execution(* hello.SpringAOP.member.MemberService.*(..))";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(helloMethod, MemberService.class));

    }

    @Test
    @DisplayName("타입 매칭 : 자식 클래스 메서드 v1")
    void typeMatchInternalPointcut_v1() {

        String pointcutSignature = "execution(* hello.SpringAOP.member.MemberService.*(..))";
        pointcut.setExpression(pointcutSignature);

        assertThat(pointcut.matches(internalMethod, MemberService.class)).isFalse();
        log.info("Pointcut Match : {}", pointcut.matches(internalMethod, MemberService.class));

    }

    @Test
    @DisplayName("타입 매칭 : 자식 클래스 메서드 v2")
    void typeMatchInternalPointcut_v2() {

        String pointcutSignature = "execution(* hello.SpringAOP.member.MemberServiceImpl.*(..))";
        pointcut.setExpression(pointcutSignature);

        assertThat(pointcut.matches(internalMethod, MemberService.class)).isTrue();
        log.info("Pointcut Match : {}", pointcut.matches(internalMethod, MemberService.class));

    }

    // ===　파라미터 매칭

    @Test
    @DisplayName("파라미터 타입 매칭 : String")
    // (String)
    void argsMatchPointcut_String() {

        String pointcutSignature = "execution(* hello.SpringAOP.member.MemberService.*(String))";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();

    }

    @Test
    @DisplayName("파라미터 타입 매칭 : 파라미터 없음")
    // ()
    void argsMatchPointcut_NoArgs() {

        String pointcutSignature = "execution(* hello.SpringAOP.member.MemberService.*())";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isFalse();

    }

    @Test
    @DisplayName("파라미터 타입 매칭 : 정확히 하나의 파라미터만 허용 및 모든 타입 허용")
    // (x)
    void argsMatchPointcut_Star() {

        String pointcutSignature = "execution(* *(*))";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();

    }

    @Test
    @DisplayName("파라미터 타입 매칭 : 숫자와 무관하게 모든 파라미터 허용")
    // (), (xxx), (xxx, xxx)
    void argsMatchAllPointcut() {

        String pointcutSignature = "execution(* *(..))";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();

    }

    @Test
    @DisplayName("파라미터 타입 매칭 : String 타입으로 시작하고, 숫자와 무관하게 모든 타입 허용")
    // (String), (String, xxx), (String, xxx, xxx)
    void argsMatchComplexPointcut_V1() {

        String pointcutSignature = "execution(* *(String, ..))";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();

    }

    @Test
    @DisplayName("파라미터 타입 매칭 : 파라미터가 2개이며, String 타입으로 시작하고, 두번째는 모든 타입 허용")
        // (String, xxx)
    void argsMatchComplexPointcut_V2() {

        String pointcutSignature = "execution(* *(String, ..))";
        String pointcutSignature2 = "execution(* *(String, *))";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();

    }

}
