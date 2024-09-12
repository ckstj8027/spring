package hello.jdbc.exception;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
@Slf4j
public class UnCheckedAppTest {
    @Test
    void unChecked() {

        Controller controller = new Controller();
        Assertions.assertThatThrownBy(()->controller.request())
                .isInstanceOf(Exception.class);
    }


    @Test
    void printEx(){
        Controller controller = new Controller();
        try{
            controller.request();
        }
        catch (Exception e){
            log.info("ex",e);

        }
    }



    static class Controller{
       Service service=new Service();

        public void request() {
            service.logic();
        }


    }



    static class Service{
        NetworkClient networkClient= new NetworkClient();

        Repository repository=new Repository();

        public void logic()  {
            repository.call();
            networkClient.call();
        }



    }

    static class NetworkClient{

        void call()  {
            throw new RuntimeConnectException("연결실패");
        }
    }

    static class Repository{

        void call()  {
            try {
                runSql();
            } catch (SQLException e) {
                throw new RuntimeSqlException(e);
            }

        }

        public void runSql() throws SQLException {

            throw new SQLException("ex");
        }
    }

    static class RuntimeConnectException extends RuntimeException{
        public RuntimeConnectException(String message) {
            super(message);
        }
    }


    static class RuntimeSqlException extends RuntimeException{
        public RuntimeSqlException(Throwable cause) {
            super(cause);
        }
    }




}
