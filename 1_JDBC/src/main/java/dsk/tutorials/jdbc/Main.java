package dsk.tutorials.jdbc;

import dsk.tutorials.jdbc.common.ConnectionFactory;
import dsk.tutorials.jdbc.common.TablesFactory;
import dsk.tutorials.jdbc.dao.UserDao;
import dsk.tutorials.jdbc.entity.UserEntity;
import dsk.tutorials.jdbc.factory.HikariDataSourceConnectionFactory;
import dsk.tutorials.jdbc.factory.PostgreSQLTablesFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Main {

    // https://github.com/dskichigin/tutorials.git

    public void start() {
        // jdbc:mysql://host:33060/database
        // jdbc:postgresql://host:port/database

        String dbUrl = "jdbc:postgresql://127.0.0.1/tutorials";
        String dbUser = "tutorials";
        String dbPassword = "tutorials";

        // подготавливаем необходимые классы для работы

//        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(dbUrl, dbUser, dbPassword);
        ConnectionFactory connectionFactory = new HikariDataSourceConnectionFactory(dbUrl, dbUser, dbPassword);

        TablesFactory tablesFactory = new PostgreSQLTablesFactory();
        UserDao userDao = new UserDao();

        try {
            // создаем таблицы
            Connection connection = connectionFactory.getNewConnection();
            tablesFactory.create(connection);
            connection.commit();
            connection.close();

            // сохраняем нового пользователя в базу
            connection = connectionFactory.getNewConnection();
            UserEntity user = new UserEntity();
            user.setID(1);
            user.setName("Пользователь");
            user = userDao.create(connection, user);
            connection.commit();
            connection.close();

            // читаем данные о пользователе из базы
            connection = connectionFactory.getNewConnection();
            user = userDao.read(connection, user.getID());
            connection.commit();
            connection.close();

            // обновляем данные пользователя в базе
            connection = connectionFactory.getNewConnection();
            user.setBirthday(Date.valueOf("2026-01-01"));
            user = userDao.update(connection, user);
            connection.commit();
            connection.close();

            // удаляем пользователя из базы
            connection = connectionFactory.getNewConnection();
            userDao.delete(connection, user.getID());
            connection.commit();
            connection.close();

            // удаляем таблицы
            connection = connectionFactory.getNewConnection();
            tablesFactory.delete(connection);
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().start();
    }
}