package dsk.tutorials.jdbc.factory;

import dsk.tutorials.jdbc.common.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DriverManagerConnectionFactory implements ConnectionFactory {
    private String url = null;
    private String user = null;
    private String password = null;

    public DriverManagerConnectionFactory(String url, String user, String password) {
        Objects.requireNonNull(url, "Не указана строка подключения к базе (jdbc url)");
        this.url = url;
        this.user = user;
        this.password = password;
    }
    /**
     * Создаем соединение
     * @return
     * @throws SQLException
     */
    public Connection getNewConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        // отключаем режим автоматического коммита
        connection.setAutoCommit(false);
        // устанавливаем уровень изоляции транзакции
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return connection;
    }

}
