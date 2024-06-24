package hello.SpringAOP.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public String orderItem(String itemId) {
        log.info("{} 실행", this.getClass().getSimpleName());
        orderRepository.save(itemId);

        return "ok";
    }

}
