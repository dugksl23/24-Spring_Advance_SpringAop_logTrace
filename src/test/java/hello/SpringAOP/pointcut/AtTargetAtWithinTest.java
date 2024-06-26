package hello.SpringAOP.pointcut;

import hello.SpringAOP.pointcut.atTargetAtWithin.Child;
import hello.SpringAOP.pointcut.atTargetAtWithin.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;



@Slf4j
@Import(TestConfig.class)
@SpringBootTest
public class AtTargetAtWithinTest {

    @Autowired
    private Child child;

    @Test
    public void atTargetTest() {
        log.info("child proxy : {}", child);
        child.childMethod();
        child.parentMethod();
    }


}

