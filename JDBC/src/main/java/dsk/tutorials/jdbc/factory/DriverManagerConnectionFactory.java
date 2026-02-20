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
        long time1 = System.currentTimeMillis();
        Connection connection = DriverManager.getConnection(url, user, password);
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
