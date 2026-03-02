package dsk.tutorials.jdbc.dao;

import dsk.tutorials.jdbc.entity.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    /**
     * CRUD
     * @param connection
     * @param user
     * @return
     */
    public UserEntity create(Connection connection, UserEntity user) throws SQLException {
        String sql = "INSERT INTO \"User\" (\"ID\", \"Name\", \"Birthday\") values ("+user.getID()+",'"+user.getName()+"',"+user.getBirthday()+")";

        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO \"User\" (\"ID\", \"Name\", \"Birthday\") values (?,?,?)");
        preparedStatement.setInt(1, user.getID());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setDate(3, user.getBirthday());
        int rowInserted = preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println(String.format("Количество вставленных строк в таблицу \"User\" = %d", rowInserted));
        return user;
    }
    public UserEntity read(Connection connection, Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT \"ID\", \"Name\", \"Birthday\" FROM \"User\" WHERE \"ID\"= ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        UserEntity user = null;
        while (resultSet.next()) {
            user = new UserEntity();
            user.setID(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setBirthday(resultSet.getDate(3));
            break;
        }
        resultSet.close();
        preparedStatement.close();
        return user;
    }
    public List<UserEntity> readAll(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT \"ID\", \"Name\", \"Birthday\" FROM \"User\"");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<UserEntity> users = new ArrayList<>();
        while (resultSet.next()) {
            UserEntity user =  new UserEntity();
            user.setID(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setBirthday(resultSet.getDate(3));
            users.add(user);
        }
        resultSet.close();
        preparedStatement.close();
        return users;
    }
    public UserEntity update(Connection connection, UserEntity user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE \"User\" " +
                "SET \"Name\" = ?, \"Birthday\" = ? WHERE \"ID\" = ?");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setDate(2, user.getBirthday());
        preparedStatement.setInt(3, user.getID());
        int rowUpdated = preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println(String.format("Количество обновленных строк в таблице \"User\" = %d", rowUpdated));
        return user;
    }
    public void delete(Connection connection, Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"User\" WHERE \"ID\" = ?");
        preparedStatement.setInt(1, id);
        int rowDeleted = preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println(String.format("Количество удаленных строк из таблицы \"User\" = %d", rowDeleted));
    }
}
