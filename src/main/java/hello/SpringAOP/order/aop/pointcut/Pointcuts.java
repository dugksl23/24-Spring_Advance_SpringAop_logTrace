package hello.SpringAOP.order.aop.pointcut;


import ch.qos.logback.core.model.processor.PhaseIndicator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
public class Pointcuts {

    /**
     * PointCut Signature
     * hello.aop.order 패키지를 포함한 그 하위의 모든 패키지를 대상으로 적용한다.
     */
    @Pointcut("execution(* hello.SpringAOP.order..*(..))")
    public void allOrderDomain() {
    }

    /**
     * Order Domain 의 service 클래스를 대상으로만 트래잭션 로직 포인트컷 표현식
     */
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {
    }


    /**
     * hello.aop.order 패키지와 그 하위 패키지를 포함하며, 타입 패턴 이름에 Service 가 포함되는 대상
     */
    @Pointcut("allOrderDomain() && allService()")
    public void allOrderAndAllService() {}

}
