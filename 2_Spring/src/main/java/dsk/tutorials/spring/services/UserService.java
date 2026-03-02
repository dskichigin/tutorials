package dsk.tutorials.spring.services;

import dsk.tutorials.spring.common.ITableFactory;
import dsk.tutorials.spring.dao.UserDao;
import dsk.tutorials.spring.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;

@Service
public class UserService {
    private final UserDao userDao;
    private final ITableFactory tablesFactory;


    public UserService(UserDao userDao, ITableFactory tablesFactory) {
        this.userDao = userDao;
        this.tablesFactory = tablesFactory;
    }

    public void start() {
        try {
            // создаем таблицы
            tablesFactory.create();

            // сохраняем нового пользователя в базу
            UserEntity user = new UserEntity();
            user.setID(1);
            user.setName("Пользователь");
            user = userDao.create(user);

            // читаем данные о пользователе из базы
            user = userDao.read(user.getID());

            // обновляем данные пользователя в базе
            user.setBirthday(Date.valueOf("2026-01-01"));
            user = userDao.update(user);

            // удаляем пользователя из базы
            userDao.delete(user.getID());

            // удаляем таблицы
            tablesFactory.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
