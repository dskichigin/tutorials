package dsk.tutorials.jdbc.factory;

import dsk.tutorials.jdbc.common.TablesFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTablesFactory implements TablesFactory {
    /**
     * Создание таблиц
     * @param connection
     */
    public void create(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS \"User\" (" +
                "\"ID\" INT PRIMARY KEY NOT NULL, " +
                "\"Name\" VARCHAR(255) NOT NULL, " +
                "\"Birthday\" DATE" +
                ");");
        statement.close();
    }

    /**
     * Удаление таблиц
     * @param connection
     */
    public void delete(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE \"User\";");
        statement.close();
    }
}
