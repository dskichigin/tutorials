package dsk.tutorials.jdbc.factory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dsk.tutorials.jdbc.common.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class HikariDataSourceConnectionFactory implements ConnectionFactory {
    private String url = null;
    private String user = null;
    private String password = null;

    private HikariDataSource dataSource;

    public HikariDataSourceConnectionFactory(String url, String user, String password) {
        Objects.requireNonNull(url, "Не указана строка подключения к базе (jdbc url)");
        this.url = url;
        this.user = user;
        this.password = password;

        // Настраиваем пул соединений
        HikariConfig config = new HikariConfig();
        // Database connection properties
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        // отключаем режим автоматического коммита
        config.setAutoCommit(false);

        // Доп настройки для пула
        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.setMinimumIdle(5); // Минимальное количество простаивающих соединений
        config.setMaximumPoolSize(25); // Максимальное общее количество подключений в пуле

        dataSource = new HikariDataSource(config);
    }
    /**
     * Создаем соединение
     * @return
     * @throws SQLException
     */
    public Connection getNewConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        // устанавливаем уровень изоляции транзакции
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return connection;
    }
}
