package dsk.tutorials.spring.factory;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionFactory {
    private DataSource dataSource = null;

    public ConnectionFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    /**
     * Создаем соединение
     * @return
     * @throws SQLException
     */
    public Connection getNewConnection() throws SQLException {
        long time1 = System.currentTimeMillis();
        Connection connection = dataSource.getConnection();
        long time2 = System.currentTimeMillis();
        System.out.println(String.format("Время формирования соединения составляет %d ms", time2-time1));
        // отключаем режим автоматического коммита
        connection.setAutoCommit(false);
        // устанавливаем уровень изоляции транзакции
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        System.out.println(String.format("Соединение рабочее = %b", connection.isValid(1)));
        System.out.println(String.format("Соединение закрыто = %b", connection.isClosed()));
        return connection;
    }

}
