package hello.SpringAOP.pointcut.atTargetAtWithin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public Child child() {
        return new Child();
    }

    @Bean
    public Parent parent() {
        return new Parent();
    }

    @Bean
    public AtTargetAtWithinAspect testAspect() {
        return new AtTargetAtWithinAspect();
    }


}
