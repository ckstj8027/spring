package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {



    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){

        ModelAndView mav=new ModelAndView("response/hello")
                .addObject("data","hello");

        return mav;


    }

    // 도대체 모델하고 모델앤뷰 차이점이 머지 ?
    @RequestMapping("/response-view-v3")
    public ModelAndView responseViewV3(ModelAndView mav){
        mav.setViewName("response/hello");
        mav.addObject("data","hello234");
        return mav;

    }



    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){

        model.addAttribute("data","hello");

        return "response/hello";


    }



 //  이건 권장 안함
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){

        model.addAttribute("data","hello");




    }
}
