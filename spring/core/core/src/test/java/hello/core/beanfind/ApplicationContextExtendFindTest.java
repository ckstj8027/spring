package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendFindTest {


   AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모타입으로 조회시 자식이 둘 이상 있으면, 중복 오류가 발생한다 ")
    void findBeanByParentTypeDuplicate(){
       // DiscountPolicy bean = ac.getBean(DiscountPolicy.class);

        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,()->ac.getBean(DiscountPolicy.class));

    }
    @Test
    @DisplayName("부모타입으로 조회시 자식이 둘 이상 있으면, 빈이름을 지정하면된다 ")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);

    }

    @Test
    @DisplayName("특정하위타입으로 조회")
    void findBeanBySubType(){

        DiscountPolicy bean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

        org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);




    }
    @Test
    @DisplayName("부모타입으로 모두조회하기")
    void findBeanByParentType()
    {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);

        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = "+ beansOfType.get(key) );

        }
    }


    @Test
    @DisplayName("Object 타입으로 모두조회하기")
    void findBeanByObjectType()
    {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

       // org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(16);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = "+ beansOfType.get(key) );

        }
    }




    @Configuration
    static class TestConfig{

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }



    }
}
