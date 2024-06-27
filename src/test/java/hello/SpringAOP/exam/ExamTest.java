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

        for (int i = 0; i <= 5; i++) {
            examService.orderItem("요청" + i);
        }

    }
}
