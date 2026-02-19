package dsk.tutorials.jdbc.common;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {
    Connection getNewConnection() throws SQLException;
}
