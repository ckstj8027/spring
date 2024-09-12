package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;


public class ComponentFilterAppConfigTest {

    @Test
    void  filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean(BeanA.class);

        Assertions.assertThat(beanA).isNotNull();

       // BeanB beanB = ac.getBean(BeanB.class);




    }

        @Configuration
        @ComponentScan (
                includeFilters =@ComponentScan.Filter(type= FilterType.ANNOTATION,classes=MyIncludeComponent.class),
                excludeFilters =@ComponentScan.Filter(type= FilterType.ANNOTATION,classes=MyExcludeComponent.class)


        )

        static class ComponentFilterAppConfig{



    }




}
