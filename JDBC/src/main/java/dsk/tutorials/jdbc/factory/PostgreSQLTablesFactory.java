package dsk.tutorials.jdbc.factory;

import dsk.tutorials.jdbc.common.TablesFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLTablesFactory implements TablesFactory {
    /**
     * Создание таблиц
     * @param connection
     */
    public void create(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS \"User\" (" +
                "\"ID\" INT NOT NULL, " +
                "\"Name\" VARCHAR(255) NOT NULL, " +
                "\"Birthday\" DATE," +
                "CONSTRAINT \"UserPK\" PRIMARY KEY (\"ID\")" +
                ");");
        statement.close();
    }
    /**
     * Удаление таблиц
     * @param connection
     */
    public void delete(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS \"User\";");
        statement.close();
    }
}
