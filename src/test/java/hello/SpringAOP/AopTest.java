package hello.SpringAOP;


import hello.SpringAOP.order.OrderRepository;
import hello.SpringAOP.order.OrderService;
import hello.SpringAOP.order.aop.AspectV1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework. boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(AspectV1.class)
// @Import 어노테이션 자체가 @Bean 스프링 빈으로 등록하는 과정까지 자동화한다.
public class AopTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;


    /**
     * 스프링 빈으로 실제 객체가 적용되었기에
     * 모두 False 반환
     */
    @Test
    void AopInfo() {
        log.info("AopProxy : OrderService = {}", AopUtils.isAopProxy(orderService));
        log.info("orderService = {}", orderService);
        log.info("AopProxy : OrderRepository = {}", AopUtils.isAopProxy(orderRepository));
        log.info("orderRepository = {}", orderRepository);
    }

    @Test
    void success(){
        String itemId = "success";
        orderService.orderItem(itemId);
    }

    @Test
    void exception(){
        String itemId = "ex";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.orderItem(itemId);
        });
    }

}
