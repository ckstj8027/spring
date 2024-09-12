package hello.hello_spring.controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello");
        return "hello";
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name,Model model   ){
        model.addAttribute("name",name);
        return "hello-template";
    }
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello"+name;//"hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name" ) String name){
        Hello hello=new Hello();
        hello.setName(name);
        return hello;
    }






    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    @GetMapping("hello-api1")
    @ResponseBody
    public Hello1 helloApi(@RequestParam("name") String name, @RequestParam("value") int value) {
        Hello1 hello = new Hello1();
        hello.setName(name);
        hello.setValue(value);
        return hello;
    }



    public static class Hello1 {
        private String name;
        private int value;

        // Getter and Setter for name
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // Getter and Setter for value
        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }



}
