package hello.SpringAOP.internalCall;

import hello.SpringAOP.exam.annotation.Retry;
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
public class CallServiceV2 {

    private final ApplicationContext applicationContext;
    private final ObjectProvider<CallServiceV2> callServiceProvider;


    @Trace
    public void externalCall(){
        log.info("externalCall");
        // ApplicationContext
        CallServiceV2 callServiceV2 = (CallServiceV2) applicationContext.getBean("callServiceV2");
        // CallServiceProvider = 지연조회
        callServiceProvider.getObject().internalCall();
    }



    @Trace
    public void internalCall(){
        log.info("internalCall");
    }

}
