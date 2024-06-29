package hello.SpringAOP.internalCall;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class CallServiceV2Test {

    @Autowired
    private CallServiceV2 callServiceV2;

    @Test
    void callServiceV2() {
        log.info("callServiceV1 {}", callServiceV2.getClass());
        callServiceV2.externalCall();
    }


}