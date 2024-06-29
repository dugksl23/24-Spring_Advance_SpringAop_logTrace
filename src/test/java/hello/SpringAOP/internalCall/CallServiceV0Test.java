package hello.SpringAOP.internalCall;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CallServiceV0Test {

    @Autowired
    private CallServiceV0 callServiceV0;

    @Test
    public void externalCallTest() {

        log.info("callService : {}", callServiceV0.getClass());
        callServiceV0.externalCall();

    }

    @Test
    public void internalCallTest() {
        log.info("callService : {}", callServiceV0.getClass());
        callServiceV0.internalCall();

    }


}