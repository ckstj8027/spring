package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name="requestParamServlet",urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("requestParamServlet.service");
        request.getParameterNames();

        System.out.println("[전체 파라미터 조회] -start");

        request.getParameterNames().asIterator().forEachRemaining(x-> System.out.println(x+"="+request.getParameter(x)));

        System.out.println("[전체 파라미터 조회] -end");


        System.out.println("[단일 파라미터 조회] -start");

        String username = request.getParameter("username");

        String age = request.getParameter("age");

        System.out.println("username = " + username);

        System.out.println("age = " + age);

        System.out.println("[단일 파라미터 조회] -end");

        System.out.println("이름이 같은 복수 파라미터 ");

        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {

            System.out.println("uesrname = " + name);

        }

        response.getWriter().write("ok");
    }
}
