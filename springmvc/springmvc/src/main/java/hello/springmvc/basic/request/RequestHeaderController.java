package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;



import java.util.Locale;
import java.util.Map;

@Slf4j
@RestController
public class RequestHeaderController {

    @GetMapping("/headers")
    public  String headers(HttpServletRequest request,
                           HttpServletResponse response,
                           HttpMethod httpMethod,
                           Locale locale,
                           @RequestHeader MultiValueMap<String ,String> headerMap,
                           @RequestHeader("host") String host,
                           @CookieValue(value="myCokkie" ,required = false) String cookie)



    {log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);
        return "ok";

    }
}
