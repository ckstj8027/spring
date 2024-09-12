package hello.core.scan.filter;


import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 컴포넌트 스캔에 추가하는 어노테이션
public @interface MyIncludeComponent {
}
