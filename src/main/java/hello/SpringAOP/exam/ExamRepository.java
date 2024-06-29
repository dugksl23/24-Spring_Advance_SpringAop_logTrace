package hello.SpringAOP.exam;

import hello.SpringAOP.exam.annotation.Retry;
import hello.SpringAOP.exam.annotation.Trace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Retention;

@Repository
@Slf4j
public class ExamRepository {

    private static int sequence = 0;

    /**
     * 5번에 1번 실패하는 요청
     */
    @Retry(value = 4)
    @Trace
    public String save(String itemId) {
        sequence++;
        log.info("sequence : {}", sequence);
        if (sequence % 5 == 0) {
            throw new IllegalArgumentException("실패 " + itemId);
        }

        return "okay";
    }


}
