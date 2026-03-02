package dsk.tutorials.spring.factory;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.util.Objects;

public class DataSourceFactory {
    private PGSimpleDataSource dataSource = null;

    public DataSourceFactory(String url, String user, String password) {
        Objects.requireNonNull(url, "Не указана строка подключения к базе (jdbc url)");
        dataSource = new PGSimpleDataSource();
        dataSource.setURL(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
    }
    public DataSource getDataSource() {
        return dataSource;
    }
}
