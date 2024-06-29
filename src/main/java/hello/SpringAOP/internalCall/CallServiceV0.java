package hello.SpringAOP.internalCall;

import hello.SpringAOP.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CallServiceV0 {

    @Trace
    public void externalCall(){
        log.info("externalCall");
        this.internalCall();
    }

    @Trace
    public void internalCall(){
        log.info("internalCall");
    }

}
