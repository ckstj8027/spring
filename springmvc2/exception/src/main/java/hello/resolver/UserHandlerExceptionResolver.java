package hello.resolver;


import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j

public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try{
            if(ex instanceof UserException){
                log.info("userException resolver to 400");
                String acceptHeader = request.getHeader("accept");

               // response.sendError(400); // 이경우는 베이직에러컨트롤러의 /error 로 가서 스프링에서 제공하는 json 형태를 반환 ,sendError 메서드는 응답을 즉시 완료시키기 때문에 이후의 코드 블록은 무시됩니다
                response.setStatus(400);
                if("application/json".equals(acceptHeader)){
                    Map<String,Object> errorResult=new HashMap<>();
                    errorResult.put("ex",ex.getClass());
                    errorResult.put("message",ex.getMessage());
                    String result = objectMapper.writeValueAsString(errorResult);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(result);
                    return new ModelAndView();


                }
                else {
                    //TEXT/HTML
                    return new ModelAndView();


                  //  return new ModelAndView("error/404");

                }
        }
        }
        catch (IOException e){
            log.error("resolver ex",e);
        }
        return null;
    }
}
