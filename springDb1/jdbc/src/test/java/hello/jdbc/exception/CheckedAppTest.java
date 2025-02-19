package hello.jdbc.exception;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

public class CheckedAppTest {

    @Test
    void checked() throws SQLException, ConnectException {

        Controller controller = new Controller();
        Assertions.assertThatThrownBy(()->controller.request())
                .isInstanceOf(Exception.class);
    }



    static class Controller{
        Service service=new Service();

        public void request() throws SQLException, ConnectException {
            service.logic();
        }


    }



    static class Service{
        NetworkClient networkClient= new NetworkClient();

        Repository repository=new Repository();

        public void logic() throws ConnectException, SQLException {
            repository.call();
            networkClient.call();
        }



    }

    static class NetworkClient{

        void call() throws ConnectException {
            throw new ConnectException();
        }
    }

    static class Repository{

        void call() throws SQLException {
            throw new SQLException();
        }
    }

}
