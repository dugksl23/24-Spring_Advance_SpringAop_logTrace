package hello.SpringAOP.exam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ExamTest {

    @Autowired
    private ExamService examService;


    @Test
    public void test() {

        for (int i = 1; i <= 5; i++) {
            String s = examService.orderItem("요청 " + i);
            log.info("응답 : {}", s);
        }

    }
}
