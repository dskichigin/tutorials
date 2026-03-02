package dsk.tutorials.spring.dao;

import dsk.tutorials.spring.entity.UserEntity;
import dsk.tutorials.spring.factory.ConnectionFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private final ConnectionFactory connectionFactory;

    public UserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
    /**
     * CRUD
     * @param user
     * @return
     */
    public UserEntity create(UserEntity user) throws SQLException {
        Connection connection = connectionFactory.getNewConnection();
        String sql = "INSERT INTO \"User\" (\"ID\", \"Name\", \"Birthday\") values ("+user.getID()+",'"+user.getName()+"',"+user.getBirthday()+")";

        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO \"User\" (\"ID\", \"Name\", \"Birthday\") values (?,?,?)");
        preparedStatement.setInt(1, user.getID());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setDate(3, user.getBirthday());
        int rowInserted = preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println(String.format("Количество вставленных строк в таблицу \"User\" = %d", rowInserted));
        connection.commit();
        connection.close();
        return user;
    }
    public UserEntity read(Integer id) throws SQLException {
        Connection connection = connectionFactory.getNewConnection();
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
        connection.commit();
        connection.close();
        return user;
    }
    public List<UserEntity> readAll() throws SQLException {
        Connection connection = connectionFactory.getNewConnection();
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
        connection.commit();
        connection.close();
        return users;
    }
    public UserEntity update(UserEntity user) throws SQLException {
        Connection connection = connectionFactory.getNewConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE \"User\" " +
                "SET \"Name\" = ?, \"Birthday\" = ? WHERE \"ID\" = ?");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setDate(2, user.getBirthday());
        preparedStatement.setInt(3, user.getID());
        int rowUpdated = preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println(String.format("Количество обновленных строк в таблице \"User\" = %d", rowUpdated));
        connection.commit();
        connection.close();
        return user;
    }
    public void delete(Integer id) throws SQLException {
        Connection connection = connectionFactory.getNewConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM \"User\" WHERE \"ID\" = ?");
        preparedStatement.setInt(1, id);
        int rowDeleted = preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println(String.format("Количество удаленных строк из таблицы \"User\" = %d", rowDeleted));
        connection.commit();
        connection.close();
    }
}
