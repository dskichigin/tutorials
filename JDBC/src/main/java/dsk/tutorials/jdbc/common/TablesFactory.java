package dsk.tutorials.jdbc.common;

import java.sql.Connection;
import java.sql.SQLException;

public interface TablesFactory {
    void create(Connection connection) throws SQLException;
    void delete(Connection connection) throws SQLException;
}
