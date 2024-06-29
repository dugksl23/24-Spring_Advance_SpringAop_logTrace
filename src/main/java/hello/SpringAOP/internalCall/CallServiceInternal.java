package hello.SpringAOP.internalCall;

import hello.SpringAOP.exam.annotation.Trace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceInternal {

    @Trace
    public void internalCall() {
        log.info("internalCall");
    }

}
