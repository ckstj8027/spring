package hello.jdbc.connetion;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.connection.ConnectionConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {

        Connection con1 = DriverManager.getConnection(ConnectionConst.URL, ConnectionConst.USERNAME, ConnectionConst.PASSWORD);
        Connection con2 = DriverManager.getConnection(ConnectionConst.URL, ConnectionConst.USERNAME, ConnectionConst.PASSWORD);
        log.info("Connection :{} class:{} ",con1,con1.getClass());
        log.info("Connection :{} class:{} ",con2,con2.getClass());
    }
    @Test
    void dataSourceDriverManager() throws SQLException {

        //기존 DriverManage를 통해서 커넥션을 획득하는 방법과  DataSource를 통해서 커넥션을 획득하는방법에는 차이가있다

        DataSource dataSource = new DriverManagerDataSource(ConnectionConst.URL, ConnectionConst.USERNAME, ConnectionConst.PASSWORD);
        useDataSource(dataSource);
    }
    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(ConnectionConst.URL);
        dataSource.setUsername(ConnectionConst.USERNAME);
        dataSource.setPassword(ConnectionConst.PASSWORD);
        dataSource.setPoolName("MyPool");

        useDataSource(dataSource);
        Thread.sleep(1000);
    }





    private void useDataSource(DataSource dataSource) throws SQLException {
        //기존 DriverManage를 통해서 커넥션을 획득하는 방법과  DataSource를 통해서 커넥션을 획득하는방법에는 차이가있다
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        log.info("Connection :{} class:{} ",con1,con1.getClass());
        log.info("Connection :{} class:{} ",con2,con2.getClass());

    }



}
