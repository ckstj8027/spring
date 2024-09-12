package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;



    @Configuration// 스프링 bean 이 싱글톤을 유지할수있도록 처리한다  바이트코드의 마법 -> 사실 빈에 Appconfig 클래스가 등록되는게 아닌
    // 그걸 상속받은 Appconfig@cglib 이런 클래스를 받아서 등록
    //그 임의의 다른 클래스가 바로 싱글톤이 보장되도록 해준다. 아마도 다음과 같이 바이트 코드를 조작해서 작성되어 있을
    //것이다.(실제로는 CGLIB의 내부 기술을 사용하는데 매우 복잡하다.)
    @ComponentScan(
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)


    )
    public class AutoAppConfig{
//        @Bean(name="memoryMemberRepository")
//        MemberRepository memberRepository(){
//            return new MemoryMemberRepository();
//        }

        // 이렇게 되면 수동 등록된 bean 이 우선권을 가짐 수동빈이 자동빈을 오버라이드 해버림

    }










