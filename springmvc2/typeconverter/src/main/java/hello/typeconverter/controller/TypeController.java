package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class TypeController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request){
        String data=request.getParameter("data");
        Integer intValue = Integer.valueOf(data);
        System.out.println("intValue = " + intValue);
        return "ok";
    }

    @GetMapping("/hello-v2")
    public String helloV2(HttpServletRequest request , @RequestParam Integer data) {
        System.out.println("data = " + data);
        return "ok";

    }
    @GetMapping("/ip-port")
    public String helloV3(@ModelAttribute IpPort ipPort){
        System.out.println("data = " + ipPort);
        return "ok";

    }

    @PostMapping("/ip-port")
    public String helloV4(@RequestBody IpPort ipPort){
        System.out.println("data = " + ipPort);
        return "ok";

    }
}
