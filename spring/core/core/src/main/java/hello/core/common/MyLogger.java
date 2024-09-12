package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


import java.util.UUID;



@Component
//@Scope(value = "request")
//요청마다 독립적인 상태를 유지할 필요가 있을 때 사용합니다. 이를 통해 요청별로 별도의 객체를 생성하여 상태를 관리
// 그런데 컨트롤러에서 요청이없을때는 객체 자체가 생성이안됨 그래서 ObjectProvider 을 사용해서 요청이 있을때만 생성하도록 함

// 프록시로 처리할수도있음
@Scope(value = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requestURL;


    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }


    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }


    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}