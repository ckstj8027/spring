package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);

        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();

        MemberRepository memberRepository2 = orderService.getMemberRepository();


        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);
    }
}
