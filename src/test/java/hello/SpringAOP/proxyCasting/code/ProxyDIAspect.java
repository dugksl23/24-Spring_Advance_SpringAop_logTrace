package hello.SpringAOP.proxyCasting.code;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@org.aspectj.lang.annotation.Aspect
@Component
public class ProxyDIAspect {

    @Before("execution(* hello.SpringAOP..*(..))")
    public void before() {
        log.info("모두 다 걸림");
    }

}
