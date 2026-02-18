package dsk.tutorials.jdbc;

import dsk.tutorials.jdbc.common.TablesFactory;
import dsk.tutorials.jdbc.dao.UserDao;
import dsk.tutorials.jdbc.entity.UserEntity;
import dsk.tutorials.jdbc.factory.ConnectionFactory;
import dsk.tutorials.jdbc.factory.PostgreSQLTablesFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Main {

    public void start() {
        // jdbc:mysql://host:33060/database
        // jdbc:postgresql://host:port/database

        String dbUrl = "jdbc:postgresql://127.0.0.1/tutorials";
        String dbUser = "tutorials";
        String dbPassword = "tutorials";

        // подготавливаем необходимые классы для работы
        ConnectionFactory connectionFactory = new ConnectionFactory(dbUrl, dbUser, dbPassword);
        TablesFactory tablesFactory = new PostgreSQLTablesFactory();
        UserDao userDao = new UserDao();

        try (Connection connection = connectionFactory.getNewConnection()){
            System.out.println(String.format("Соединение рабочее = %b", connection.isValid(1)));
            System.out.println(String.format("Соединение закрыто = %b", connection.isClosed()));

            // создаем таблицы
            tablesFactory.create(connection);

            // сохраняем нового пользователя в базу
            UserEntity user = new UserEntity();
            user.setID(1);
            user.setName("Пользователь");
            user = userDao.create(connection, user);

            // читаем данные о пользователе из базы
            user = userDao.read(connection, user.getID());

            // обновляем данные пользователя в базе
            user.setBirthday(Date.valueOf("2026-01-01"));
            user = userDao.update(connection, user);

            // удаляем пользователя из базы
            userDao.delete(connection, user.getID());

            // удаляем таблицы
            tablesFactory.delete(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main().start();
    }
}