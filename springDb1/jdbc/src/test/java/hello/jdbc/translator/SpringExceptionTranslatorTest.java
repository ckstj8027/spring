package hello.jdbc.translator;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootTest
@Slf4j
public class SpringExceptionTranslatorTest {
    @Autowired
    DataSource dataSource;



    @Test
    void sqlExceptionErrorCode(){
        String sql=" select bad grammer";
        try {
            Connection con = dataSource.getConnection();
        } catch (SQLException e) {
            Assertions.assertThat(e.getErrorCode()).isEqualTo(42122);
            int errorCode=e.getErrorCode();
            log.info("errorCode={}",errorCode);
            log.info("error",e);
        }

    }

    // 위에 테스트처럼 코드를 일일이 다 체크할순없어서  심지어 데이터베이스마다 코드가 다 다름
    // 스프링에서 제공하는 SQLErrorCodeSQLExceptionTranslator
    // 스프링에서 예외 추상화를 다해놈 해당 구체적인 예외를 찾는다
    // 즉 데이터베이스 벤더에 따라 예외 코드와 메시지가 다를 수 있기 때문에, Spring은 이 문제를 해결하기 위해 예외 추상화 계층을 제공한다.
    @Test
    void exceptionTranslator(){
        String sql=" select bad grammer";

        try{

            Connection con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeQuery();


        }

        catch (SQLException e){
            SQLErrorCodeSQLExceptionTranslator exTranslator = new SQLErrorCodeSQLExceptionTranslator();
            DataAccessException resultEx = exTranslator.translate("select", sql, e);
            log.info("resultEx",resultEx);
            Assertions.assertThat(resultEx.getClass()).isEqualTo(BadSqlGrammarException.class);


        }

    }





}
