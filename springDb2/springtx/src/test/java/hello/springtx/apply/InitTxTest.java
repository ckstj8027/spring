package hello.springtx.apply;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
@Slf4j
public class InitTxTest {

    @Autowired Hello hello;

    @Test
    void go(){
        //초기화 코드는 스프링이 초기화 시점에 호출한다
       // 이렇게 직접 치면  hello.initV1(); 에 트랜잭션 걸린다
        // 근데 그냥 실행하면 안걸림

    }




    @TestConfiguration
    static class InitTxTestConfig{

        @Bean
        Hello hello(){
          return   new Hello();
    }
    }



    static class Hello{

        @PostConstruct
        @Transactional
        // 함께 쓰면 @Transactional 적용이안된다 왜냐하면 초기화 코드가 먼저 실행이 되고 그 다음에 aop 가 적용되기때문
        public void initV1(){
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Hello init @PostConstruct tx active={}",isActive);
        }

        @EventListener(ApplicationReadyEvent.class)
        @Transactional
        public void initV2(){
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("Hello init @EventListener tx active={}",isActive);
        }


    }



}
