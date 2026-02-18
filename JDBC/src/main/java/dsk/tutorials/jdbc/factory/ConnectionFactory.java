package dsk.tutorials.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private String url = null;
    private String user = null;
    private String password = null;

    public ConnectionFactory(String url, String user, String password) {
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
        System.out.println(connection.isValid(1));
        System.out.println(connection.isClosed());

        return connection;
    }
}
