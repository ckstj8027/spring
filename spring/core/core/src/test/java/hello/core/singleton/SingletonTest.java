package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);

        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 객체를 활용한 실험")
    void SingletonServiceTest(){
        SingletonService singletonservice1 = SingletonService.getInstance();
        SingletonService singletonservice2 = SingletonService.getInstance();

        Assertions.assertThat(singletonservice1).isSameAs(singletonservice2);
    }

    //그러면 하나하나 다 싱글톤으로 바꿔여 ? DI 컨테이너를 쓰면 자동으로 객체를 싱글톤으로 처리함

    @Test
    @DisplayName("스프링 컨테이너")
    void springContainer(){
        //AppConfig appConfig = new AppConfig();

        
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);


        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);


        System.out.println("memberService1 = " + memberService1);

        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }









}
