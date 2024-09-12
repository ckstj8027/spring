package hello.exception.servlet;


import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@Slf4j

public class ServletExceptionController  {
    //error-
    @GetMapping("/error-ex")
    public void errorEx(){
        throw new RuntimeException("예외발생");

        //익셉션 터지면 그냥 무조건 500 에러
    }
    //send error 인 경우는 상태 오류 코드 지정해서 나갈수있음

    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404,"404 에러");

    }


    @GetMapping("/error-406")
    public void error406(HttpServletResponse response) throws IOException {
        response.sendError(406,"406 에러");

    }




    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500,"500 에러");

    }
}
