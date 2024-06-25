package hello.SpringAOP.member.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // value 를 runtime 설정시 런타임에 동적으로 실행 가능
public @interface MethodAop {

    String value() default "";
}
