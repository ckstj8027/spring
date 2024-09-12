package hello.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
public class MyHandlerExceptionResolver  implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try{

            // 모델앤뷰를 반환하는데 아무것도없다 ->할거없네 ? -> aftercompletion 은 호출되고 was 까지 정상 리턴 근데 까보니까 400 이있네 다시 내려와서~ 오류페이지뒤짐
            //was 에 exception 을 던지는게 아니라 오류코드를 던짐
            if(ex instanceof IllegalArgumentException){
                log.info("illegalArgument: 400");
                response.sendError(400,ex.getMessage());




                return new ModelAndView();

            }



        }

        catch (Exception e){
            log.error(" 리조버익셉션 예외   ");
        }
        return  null;





    }
}
