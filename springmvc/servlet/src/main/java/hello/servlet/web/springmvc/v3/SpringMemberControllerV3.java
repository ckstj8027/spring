package hello.servlet.web.springmvc.v3;



import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {


    //@RequestMapping("new-form")
    @GetMapping("new-form")
    public String newForm(){
        return  "new-form";
    }


    MemberRepository memberRepository=MemberRepository.getInstance();

    //@RequestMapping("save")
    @PostMapping("save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {


        Member member = new Member(username, age);
        System.out.println("member = " + member);
        memberRepository.save(member);
      //  ModelAndView mv = new ModelAndView("save-result");
     //   mv.addObject("member", member);
        model.addAttribute("member",member);
        return "save-result";
    }

    //@RequestMapping
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
      //  ModelAndView mv = new ModelAndView("members");
       // mv.addObject("members", members);
        model.addAttribute("members",members);

        return "members";
    }

}