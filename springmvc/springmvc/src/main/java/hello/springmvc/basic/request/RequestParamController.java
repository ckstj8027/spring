package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
//@RestController
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requsetParamV1(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");


        int age = Integer.parseInt(request.getParameter("age"));


        log.info(" username={} age= {}",username,age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public  String requestParamV2(@RequestParam("username") String memberName,
                                  @RequestParam("age") int memberAge){


        log.info(" username={} age= {}",memberName,memberAge);

        return  "ok";

    }


    @ResponseBody
    @RequestMapping("/request-param-v3")
    public  String requestParamV3(@RequestParam String username,
                                  @RequestParam int age){


        log.info(" username={} age= {}",username,age);

        return  "ok";

    }


    @ResponseBody
    @RequestMapping("/request-param-v4")
    public  String requestParamV4( String username, int age){


        log.info(" username={} age= {}",username,age);

        return  "ok";

    }


    @ResponseBody
    @RequestMapping("/request-param-required")
    public  String requestParamRequired( @RequestParam(required = true) String username,
                                         @RequestParam(required = false) Integer age){


        log.info(" username={} age= {}",username,age);

        return  "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public  String requestParamDefault( @RequestParam(required = true,defaultValue = "guest") String username,
                                         @RequestParam(required = false,defaultValue = "-1") int age){


        log.info(" username={} age= {}",username,age);

        return  "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public  String requestParamMap( @RequestParam Map<String, Object> paramap){


        log.info(" username={} age= {}",paramap.get("username"),paramap.get("age"));

        return  "ok";

    }

//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public  String modelAttributeV1(@RequestParam String username,@RequestParam int age){
//        HelloData data=new HelloData();
//        data.setUsername(username);
//        data.setAge(age);
//
//        log.info(" username={} age= {}",data.getUsername(),data.getAge());
//        return "ok";
//
//
//    }


//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public  String modelAttributeV1(@ModelAttribute HelloData data){
//
//
//        log.info(" username={} age= {}",data.getUsername(),data.getAge());
//
//        return "ok";
//
//
//    }


    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public HelloData modelAttributeV1( HelloData data) {
        log.info("username={} age={}", data.getUsername(), data.getAge());
        return data;
    }









    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public  String modelAttributeV2(HelloData data){


        log.info(" username={} age= {}",data.getUsername(),data.getAge());
        return "ok";


    }








}
