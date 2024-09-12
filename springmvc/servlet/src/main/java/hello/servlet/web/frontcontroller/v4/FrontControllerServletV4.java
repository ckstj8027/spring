package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.comtroller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.comtroller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.comtroller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(name="frontControllerServletV4",urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap =new HashMap<>();

    public FrontControllerServletV4() {

        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());

        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());

        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        System.out.println(requestURI);

        ControllerV4 controller = controllerMap.get(requestURI);

        if (controller ==null){
            response.setStatus(404);
            return;
        }

        //paraMap
        Map<String, String> paraMap = creatParaMap(request);

        Map<String, Object> model=new HashMap<>();   // 추가

        String viewName = controller.process(paraMap, model);

       // String viewName = mv.getViewName();  // new-form , member ,save-result  // 이부분 삭제

        MyView view = viewResolver(viewName);

        view.render(model,request,response);


    }

    private static MyView viewResolver(String viewName) {

        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> creatParaMap(HttpServletRequest request) {
        Map<String ,String> paraMap=new HashMap<>();

//        Iterator<String> iterator = request.getParameterNames().asIterator();
//        while (iterator.hasNext()) {
//            String paramName = iterator.next();
//            System.out.println("Parameter Name: " + paramName);
//        }




        System.out.println();

        request.getParameterNames().asIterator().forEachRemaining(paraName->paraMap.put(paraName, request.getParameter(paraName)));


//        Parameter Name: kim
//        Parameter Age: 33

        return paraMap;
    }
}
