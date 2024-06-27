package hello.SpringAOP.exam;

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
    @Trace
    public String save(String itemId) {
        sequence++;
        if (sequence % 5 == 0) {
            throw new IllegalArgumentException("요청 실패 " + itemId);
        }
        log.info("sequence : {}", sequence);
        return "okay";
    }


}