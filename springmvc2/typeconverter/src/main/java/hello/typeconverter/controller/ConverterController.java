package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConverterController {




    @GetMapping("/converter-view")
    public String converterView(Model model){

        model.addAttribute("number",10000);
        model.addAttribute("ipPort",new IpPort("127.0.0.1",8000));
        return "converter-view";

    }
    @GetMapping("/converter/edit")
    public String converterForm(Model model){
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        Form form = new Form(ipPort);
        model.addAttribute("form",form);
        return "converter-form";
        // 보여줄땐 객체를 문자로 변환
    }

    @PostMapping("/converter/edit")
    public String converterEdit(@ModelAttribute Form form  , Model model){
        IpPort ipPort = form.getIpPort();
        //"127.0.0.1" 이 문자인데  @ModelAttribute 가 내부적으러 conversionService 를 사용하여
        //StringToIpPortConverter 를 호출해서 Form 분명 문자열로 받았는데 IpPort 타입으로 넣어줌

        model.addAttribute("ipPort",ipPort);
        return "converter-view";
    }




    @Data
    static  class Form{
        private IpPort ipPort;

        public Form(IpPort ipPort) {
            this.ipPort = ipPort;
        }
    }





}
