package dsk.tutorials.spring.common;

import java.sql.SQLException;

public interface ITableFactory {
    void create() throws SQLException;
    void delete() throws SQLException;
}
