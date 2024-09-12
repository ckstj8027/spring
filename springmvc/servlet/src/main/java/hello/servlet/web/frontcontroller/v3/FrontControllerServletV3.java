package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;


import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@WebServlet(name="frontControllerServletV3",urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap =new HashMap<>();

    public FrontControllerServletV3() {

        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());

        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());

        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        System.out.println(requestURI);

        ControllerV3 controller = controllerMap.get(requestURI);

        if (controller ==null){
            response.setStatus(404);
            return;
        }

        //paraMap
        Map<String, String> paraMap = creatParaMap(request);

        ModelView mv = controller.process(paraMap);

        String viewName = mv.getViewName();  // new-form , member ,save-result

        MyView view = viewResolver(viewName);

        view.render(mv.getModel(),request,response);


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
//        Parameter Name: 33

        return paraMap;
    }
}
