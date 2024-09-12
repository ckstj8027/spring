package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    MemberRepository memberRepository=MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> ParaMap) {
        String username = ParaMap.get("username");
        int age = Integer.parseInt(ParaMap.get("age"));

        Member member=new Member(username,age);

        memberRepository.save(member);

        ModelView mv=new ModelView("save-result");
        mv.getModel().put("member",member);
        return mv;


    }
}

