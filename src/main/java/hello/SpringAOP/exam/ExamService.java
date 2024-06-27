package hello.SpringAOP.exam;

import hello.SpringAOP.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j

public class ExamService {

    private final ExamRepository examRepository;

    @Trace
    public String orderItem(String itemId){
        String save = examRepository.save(itemId);
        return save;
    }
}
