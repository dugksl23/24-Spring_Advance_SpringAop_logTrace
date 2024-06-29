package hello.SpringAOP.internalCall;

import hello.SpringAOP.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CallServiceV3 {

    private final CallServiceInternal callServiceInternal;

    @Trace
    public void externalCall(){
        log.info("externalCall");
        callServiceInternal.internalCall();
    }

}
