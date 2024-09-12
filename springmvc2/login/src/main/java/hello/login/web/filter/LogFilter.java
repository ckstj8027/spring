package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter  implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("logflter init" );

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest    =(HttpServletRequest) request;

        String requestURI = httpRequest.getRequestURI();


        HttpServletResponse httpResponse=(HttpServletResponse) response;

        String uuid = UUID.randomUUID().toString();


        try{
            log.info("requset[{}][{}]",uuid,requestURI);
            chain.doFilter(request,response);

        }
        catch (Exception e){
            throw e;
        }
        finally {
            log.info("response[{}][{}]",uuid,requestURI);
        }





    }

    @Override
    public void destroy() {
        log.info("log filter destory");
    }
}
