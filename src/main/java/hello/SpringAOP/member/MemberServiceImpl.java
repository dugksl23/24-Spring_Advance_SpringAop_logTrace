package hello.SpringAOP.member;


import hello.SpringAOP.member.annotation.ClassAop;
import hello.SpringAOP.member.annotation.MethodAop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@ClassAop
@Service
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("test value")
    public String hello(String name) {
        log.info("hello");
        return name;
    }

    public String internalMethod(String name) {
        log.info("internalMethod");
        return "internalMethod";
    }

}
