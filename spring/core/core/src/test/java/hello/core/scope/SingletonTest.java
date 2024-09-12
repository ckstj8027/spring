package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

    @Test
    void singletonTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBeean.class);
        SingletonBeean singletonBean1 = ac.getBean(SingletonBeean.class);
        SingletonBeean singletonBean2 = ac.getBean(SingletonBeean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);
        ac.close();
    }
    @Scope("singleton")
    static class SingletonBeean {
        @PostConstruct
        public void init() {
            System.out.println("singletonBean.init ")
            ;
        }

        @PreDestroy
        public void destroy() {
            System.out.println("singletonBean.destroy");
        }



    }











}
