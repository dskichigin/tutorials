package dsk.tutorials.spring.factory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Objects;

public class HikariDataSourceFactory {
    private HikariDataSource dataSource;

    public HikariDataSourceFactory(String url, String user, String password) {
        Objects.requireNonNull(url, "Не указана строка подключения к базе (jdbc url)");

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
    public DataSource getDataSource() {
        return dataSource;
    }
}
