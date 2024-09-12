package hello.core.beanfind;

import com.sun.source.tree.LiteralTree;
import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(SameBeanConfig.class);


    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다 ")
    //NoUniqueBeanDefinitionException
    void findBeanByTypeDuplicate(){
        //MemberRepository bean = ac.getBean(MemberRepository.class);

        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,()->ac.getBean(MemberRepository.class));
    }


    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 명시적으로 지정하면된다 ")
    void findBeanByName() {

        MemberRepository memberRepository=ac.getBean("memberRepository1",MemberRepository.class);

        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);




    }
    @Test
    @DisplayName("특정타입 모두 조회하기  ")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key+", bean = " + beansOfType.get(key));

        }

    }






    @Configuration

    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }


        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }











    }



















}
