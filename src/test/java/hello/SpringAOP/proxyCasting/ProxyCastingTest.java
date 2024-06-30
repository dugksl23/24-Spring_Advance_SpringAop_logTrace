package hello.SpringAOP.proxyCasting;

import hello.SpringAOP.member.MemberService;
import hello.SpringAOP.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ProxyCastingTest {

    /**
     * 인터페이스 기반 프록시 생성 JDK 동적 프록시
     */
    @Test
    void jdkProxyCastingTest() {

        MemberServiceImpl memberService = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(memberService);
        proxyFactory.setProxyTargetClass(false); // JDK 동적 프록시

        // 프록시를 인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        // 인터페이스를 구체 클래스로 다운 캐스팅 시도 -> 실패 ClassCastingException
        // -> 불가능, 인터페이스를 구현한 것이지, 구체 클래스를 대상으로 상속 확장한 것이 아니다.
        MemberServiceImpl memberServiceProxy1 = (MemberServiceImpl) memberServiceProxy;


        Assertions.assertThrows(
                ClassCastException.class, () -> memberServiceProxy1.hello("hello"));
    }

    /**
     * 구체 클래스 기반 프록시 생성 : CGLIB
     */
    @Test
    void CGLIBProxyCastingTest() {

        MemberServiceImpl memberService = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(memberService);
        proxyFactory.setProxyTargetClass(true); // CGLIB 프록시

        // 프록시를 인터페이스로 캐스팅 성공
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        // 인터페이스를 구체 클래스로 다운 캐스팅 시도 -> 실패 ClassCastingException
        // -> 불가능, 인터페이스를 구현한 것이지, 구체 클래스를 대상으로 상속 확장한 것이 아니다.
        MemberServiceImpl memberServiceProxy1 = (MemberServiceImpl) memberServiceProxy;

        memberServiceProxy1.hello("hi");
    }

}
