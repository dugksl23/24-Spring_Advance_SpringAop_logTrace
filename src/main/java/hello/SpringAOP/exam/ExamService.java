package hello.SpringAOP.exam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamService {

    private final ExamRepository examRepository;

    public String orderItem(String itemId){
        String save = examRepository.save(itemId);
        log.info("return : {}", save);
        return save;
    }
}
