package hello.springtx.propagation;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@SpringBootTest
public class BasicTxTest {

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @TestConfiguration
    static class BasicTxTestConfig{
//        @Bean
//        DataSource dataSource(){
//            DriverManagerDataSource dataSource = new DriverManagerDataSource();
//            dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
//            return dataSource;
//
//        }
        @Bean
        DataSource dataSource(){
            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setJdbcUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
            return dataSource;

        }


        @Bean
        PlatformTransactionManager platformTransactionManager(DataSource dataSource){

            return new DataSourceTransactionManager(dataSource);
        }

    }

    @Test
    void commit(){
        log.info("트랜잭션 시작");
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());

        log.info("트랜잭션 커밋 시작");
        platformTransactionManager.commit(status);


    }


    @Test
    void rollBack(){
        log.info("트랜잭션 시작");
        TransactionStatus status = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());

        log.info("트랜잭션 롤백 시작");
        platformTransactionManager.rollback(status);


    }

    @Test
    void doubleCommit(){
        log.info("트랜잭션1 시작");
        TransactionStatus status1 = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());

        log.info("트랜잭션1 커밋시작");
        platformTransactionManager.commit(status1);

        log.info("트랜잭션2 시작");
        TransactionStatus status2 = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());

        log.info("트랜잭션2 커밋시작");
        platformTransactionManager.commit(status2);



    }

    @Test
    void innerCommit(){  // 둘다 커밋일경우 뭐 에상대로 잘 됨
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        log.info("outer.isNewTransaction()={}",outer.isNewTransaction());


        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        log.info("inner.isNewTransaction()={}",inner.isNewTransaction());

        log.info("내부 트랜잭션 커밋");
        platformTransactionManager.commit(inner);


        log.info("외부 트랜잭션 커밋");
        platformTransactionManager.commit(outer);
    }

    /////////////////////////////////// 이제 한쪽만 롤백인 경우 경우의 수를 살펴보자
    @Test
    void outerRollback(){  // 밖에만 롤백인경우
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        log.info("outer.isNewTransaction()={}",outer.isNewTransaction());


        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        log.info("inner.isNewTransaction()={}",inner.isNewTransaction());

        log.info("내부 트랜잭션 커밋");
        platformTransactionManager.commit(inner);


        log.info("외부 트랜잭션 롤백");
        platformTransactionManager.rollback(outer);
    }


    @Test
    void innerRollback(){  // 안만 롤백인경우
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        log.info("outer.isNewTransaction()={}",outer.isNewTransaction());


        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        log.info("inner.isNewTransaction()={}",inner.isNewTransaction());

        log.info("내부 트랜잭션 롤백");
        platformTransactionManager.rollback(inner);  //  내부입장에서는 내가 롤백을 해야하는데 물리 트랜젝션에 손을못대니 트랜잭션 동기화 매니저에 rollback-only 라고 표시를 해둠


        log.info("외부 트랜잭션 커밋");
       // platformTransactionManager.commit(outer);  // 어 커밋이네하고 커밋할려고보니 근데 이때먼저 트랜잭션 매니저에 rollback-only가 true 인지 아닌지를 판단 근데 true 네 -> 롤백해야지
        //UnexpectedRollbackException

        Assertions.assertThatThrownBy(()->platformTransactionManager.commit(outer))
                .isInstanceOf(UnexpectedRollbackException.class);
    }


    @Test
    void innerRollbackRequiresNew() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
        log.info("outer.isNewTransaction()={}", outer.isNewTransaction());


        log.info("내부 트랜잭션 시작");
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus inner = platformTransactionManager.getTransaction(definition);
        log.info("outer.isNewTransaction()={}", inner.isNewTransaction()); //true

        log.info("내부 트랜잭션 롤백");
        platformTransactionManager.rollback(inner);


        log.info("외부 트랜잭션 커밋");
        platformTransactionManager.commit(outer);

    }


}
