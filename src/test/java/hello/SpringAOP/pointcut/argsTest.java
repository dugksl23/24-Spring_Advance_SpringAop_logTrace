package hello.SpringAOP.pointcut;

import hello.SpringAOP.member.MemberService;
import hello.SpringAOP.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class argsTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    /**
     * @execution (* * ( java.io.Serializable)): 메서드의 시그니처로 판단 (정적)
     * @args(java.io.Serializable): 런타임에 전달된 객체 파라미터로 판단 (동적)
     */


    @Test
    @DisplayName("파라미터 타입 매칭 : String")
    // (String)
    void argsMatchPointcut_String() {

        String pointcutSignature = "args(String)";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();

    }

    @Test
    @DisplayName("파라미터 타입 매칭 : java.io.Serializable")
        // (String)
    void argsMatchPointcut_SuperTypeV1() {

        String pointcutSignature = "args(java.io.Serializable)";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();

    }

    @Test
    @DisplayName("파라미터 타입 매칭 : Object ")
        // (String)
    void argsMatchPointcut_SuperTypeV2() {

        String pointcutSignature = "args(Object)";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();

    }

    @Test
    @DisplayName("파라미터 타입 매칭 : java.io.Serializable")
    // (String)
    // execution 지시자는 args 표현식에서 타입을 정확히 매칭해야 하며, 부모타입은 허용하지 않는다.
    void executionArgsMatchPointcut_SuperType() {

        String pointcutSignature =
                "execution(* hello(java.io.Serializable))";
//                 "execution(* hello(String))";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isFalse();

    }

    @Test
    @DisplayName("파라미터 타입 매칭 : 파라미터 없음")
        // ()
    void argsMatchPointcut_NoArgs() {

        String pointcutSignature = "args()";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isFalse();

    }

    @Test
    @DisplayName("파라미터 타입 매칭 : 정확히 하나의 파라미터만 허용 및 모든 타입 허용")
        // (x)
    void argsMatchPointcut_Star() {

        String pointcutSignature = "args(*)";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();

    }

    @Test
    @DisplayName("파라미터 타입 매칭 : 숫자와 무관하게 모든 파라미터 허용")
        // (), (xxx), (xxx, xxx)
    void argsMatchAllPointcut() {

        String pointcutSignature = "args(..)";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();

    }

    @Test
    @DisplayName("파라미터 타입 매칭 : String 타입으로 시작하고, 숫자와 무관하게 모든 타입 허용")
        // (String), (String, xxx), (String, xxx, xxx)
    void argsMatchComplexPointcut_V1() {

        String pointcutSignature = "args(String, ..)";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isTrue();

    }

    @Test
    @DisplayName("파라미터 타입 매칭 : 파라미터가 2개이며, String 타입으로 시작하고, 두번째는 모든 타입 허용")
        // (String, xxx)
    void argsMatchComplexPointcut_V2() {

        String pointcutSignature = "args(String, *)";
        pointcut.setExpression(pointcutSignature);
        assertThat(pointcut.matches(helloMethod, MemberService.class)).isFalse();

    }

}
