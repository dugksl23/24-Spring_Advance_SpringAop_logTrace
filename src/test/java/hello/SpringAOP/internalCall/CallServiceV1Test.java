package hello.SpringAOP.internalCall;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class CallServiceV1Test {

    @Autowired
    private CallServiceV1 callServiceV1;

    @Test
    void callServiceV1() {
        log.info("callServiceV1 {}", callServiceV1.getClass());
        callServiceV1.externalCall();
    }

}