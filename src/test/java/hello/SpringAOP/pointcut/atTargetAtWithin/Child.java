package hello.SpringAOP.pointcut.atTargetAtWithin;

import hello.SpringAOP.member.annotation.ClassAop;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@NoArgsConstructor
@ClassAop
public class Child extends Parent {

    public void childMethod() {
        log.info("childMethod");
    }


}
