package hello.servlet.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
@Component("/spring/old-controller")
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("옛날 방식 컨트롤러 ");
        return new ModelAndView("new-form");
    }

    /**스프링 부트는
     InternalResourceViewResolver
     에 등록한라는 뷰 리졸버를 자동으로 등록하는데, 이때
     application.properties
     spring.mvc.view.prefix
     보를 사용해서 등록한다 **/
}
