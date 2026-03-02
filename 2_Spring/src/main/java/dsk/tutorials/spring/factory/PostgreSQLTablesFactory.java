package dsk.tutorials.spring.factory;

import dsk.tutorials.spring.common.ITableFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class PostgreSQLTablesFactory implements ITableFactory {
    private final ConnectionFactory connectionFactory;

    public PostgreSQLTablesFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
    /**
     * Создание таблиц
     */
    public void create() throws SQLException {
        Connection connection = connectionFactory.getNewConnection();
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS \"User\" (" +
                "\"ID\" INT NOT NULL, " +
                "\"Name\" VARCHAR(255) NOT NULL, " +
                "\"Birthday\" DATE," +
                "CONSTRAINT \"UserPK\" PRIMARY KEY (\"ID\")" +
                ");");
        statement.close();
        connection.commit();
        connection.close();
    }
    /**
     * Удаление таблиц
     */
    public void delete() throws SQLException {
        Connection connection = connectionFactory.getNewConnection();
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS \"User\";");
        statement.close();
        connection.commit();
        connection.close();
    }
}
