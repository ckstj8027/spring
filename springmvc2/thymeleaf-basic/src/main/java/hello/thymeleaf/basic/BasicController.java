package hello.thymeleaf.basic;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.expression.Temporals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("text-basic")
    public String textBasic(Model model){

        model.addAttribute("data","hello sproing");

        return  "basic/text-basic";

    }


    @GetMapping("text-unescaped")
    public String textUnescaped(Model model){

        model.addAttribute("data","<b>hello sproing</b>");

        return  "basic/text-unescaped";

    }


    @GetMapping("/variable")
    public String variable(Model model){
        User userA = new User("userA", 10);
        User userB = new User("userB", 30);

        ArrayList<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        HashMap<String ,User    > map = new HashMap<>();
        map.put("userA",userA);
        map.put("userB",userB);

        model.addAttribute("user",userA);
        model.addAttribute("users",list);
        model.addAttribute("userMap",map);

        return "/basic/variable";



    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session   , HttpServletRequest request, HttpServletResponse response ,Model model)              {
        session.setAttribute("sessionData","hello Session");

        model.addAttribute("request",request);

        model.addAttribute("paramData",request.getParameter("paramData"));

        model.addAttribute("response",response);

        model.addAttribute("servletContext",request.getServletContext());





        return "basic/basic-objects";


    }


    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";

    }

    @GetMapping("/link")
    public String link(Model model){
        model.addAttribute("param1","data1");
        model.addAttribute("param2","data2");
        return "/basic/link";

    }

    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data","spring!");
        return "/basic/literal";

    }

    @GetMapping("/operation")
    public String operation(Model model){

        model.addAttribute("null",null);
        model.addAttribute("data","spring");

        return "/basic/operation";


    }

    @GetMapping("/attribute")
    public String attribute(Model model){
        model.addAttribute("data","연습용");
        return "basic/attribute";
    }

    @GetMapping("/each")
    public  String each(Model model){
        addUser(model);

        return "/basic/each";
    }

    @GetMapping("/condition")
    public  String condition(Model model){
        addUser(model);

        return "/basic/condition";
    }

    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }
    @GetMapping("/block")
    public String block(Model model) {
        addUser(model);
        model.addAttribute("data", "Spring!");
        return "basic/block";
    }


    @GetMapping("/javascript")
    public String javascript(Model model) {
        addUser(model);
        model.addAttribute(new User("userA",10));
        model.addAttribute("data", "Spring!");
        return "basic/javascript";
    }













    @Component("helloBean")
    static class HelloBean{
        public String hello(String data){
            return "Hello"+data;
        }

    }








    private   void   addUser(Model model){
        ArrayList<User> list = new ArrayList<>();

        HashMap<Integer, User> map = new HashMap<>();

        map.put(1,new User("userA",10));
        map.put(2,new User("userB",20));
        map.put(3,new User("userC",30));

        list.add(new User("userA",10));
        list.add(new User("userB",20));
        list.add(new User("userC",30));


        model.addAttribute("users",list);
        model.addAttribute("userss",map);



    }







    @Data
    static class User{
        private  String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }




}



