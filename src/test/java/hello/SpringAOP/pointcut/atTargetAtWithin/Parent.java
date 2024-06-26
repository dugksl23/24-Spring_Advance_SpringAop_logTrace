package hello.SpringAOP.pointcut.atTargetAtWithin;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class Parent {

    public void parentMethod(){
        log.info("parentMethod");
    };

}
