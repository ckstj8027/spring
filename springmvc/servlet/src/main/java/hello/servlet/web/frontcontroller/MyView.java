package hello.servlet.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class MyView {

    private  String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException{

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException{

        modelToRequestAttribute(model, request);

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }

    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        System.out.println(" 마이 뷰에서 모델을 파라미터로 받아서 request.setAttribute(key, value) 수행");


        model.forEach((key, value) -> request.setAttribute(key, value));
        //Key: member, Value: hello.servlet.domain.member.Member@66059535
        //member  username : kim age :33

        //Key: members, Value: [hello.servlet.domain.member.Member@66059535]

        //

    }


}


