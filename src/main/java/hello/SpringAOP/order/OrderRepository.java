package hello.SpringAOP.order;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Slf4j
@Repository
public class OrderRepository {

    private Map<Long, String> repository = new ConcurrentHashMap<>();
    private AtomicLong ItemSeq = new AtomicLong();

    public void  save(String itemId) {

        log.info("{} 실행", this.getClass().getSimpleName());
        if (itemId.contains("ex")) {
            throw new IllegalArgumentException("EX 발생");
        }

        repository.put(ItemSeq.getAndIncrement(), itemId);


    }

}
