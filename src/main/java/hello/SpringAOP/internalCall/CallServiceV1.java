package hello.SpringAOP.internalCall;

import hello.SpringAOP.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1){
        this.callServiceV1 = callServiceV1;
    }

    @Trace
    public void externalCall(){
        log.info("externalCall");
        callServiceV1.internalCall();
    }



    @Trace
    public void internalCall(){
        log.info("internalCall");
    }

}
