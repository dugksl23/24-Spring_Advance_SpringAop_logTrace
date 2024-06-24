package hello.SpringAOP.order.aop;


import hello.SpringAOP.order.aop.aspects.LogTraceAspect;
import hello.SpringAOP.order.aop.aspects.TransactionAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;

@Slf4j
@Import({LogTraceAspect.class, TransactionAspect.class})
public class AspectV5Order {
}
