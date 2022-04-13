package io.github.vitalikulsha.javawebproject.dao.impl;

import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import io.github.vitalikulsha.javawebproject.user.dao.UserDao;
import io.github.vitalikulsha.javawebproject.user.entity.Role;
import io.github.vitalikulsha.javawebproject.user.entity.User;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class UserDaoImlTest {

    @Test
    public void findByLogin() throws DaoException {
        UserDao userDao = DaoFactory.instance().userDao();
        User expected = getAllUsers().stream()
                .filter(u -> u.getLogin().equals("Admin"))
                .findFirst()
                .get();
        assertEquals(expected, userDao.findByLogin("Admin"));
        assertNotEquals(expected, userDao.findByLogin("Reader"));
        assertNull(userDao.findByLogin("Test"));
        assertNotNull(userDao.findByLogin("User"));
    }

    @Test
    public void findByEmail() throws DaoException {
        UserDao userDao = DaoFactory.instance().userDao();
        User expected = getAllUsers().stream()
                .filter(u -> u.getEmail().equals("admin@gmail.com"))
                .findFirst()
                .get();
        assertEquals(expected, userDao.findByEmail("admin@gmail.com"));
        assertNotEquals(expected, userDao.findByEmail("reader@gmail.com"));
        assertNull(userDao.findByEmail("test@gmail.com"));
        assertNotNull(userDao.findByEmail("user@gmail.com"));
    }

    @Test
    public void updateAndSave() throws DaoException {
        UserDao userDao = DaoFactory.instance().userDao();
        User saveUser = new User(5, "Test", "test", "Test", "Test",
                375123456789L, "test@test.by", Role.GUEST);
        User updateUser = new User(3, "User", "04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb",
                "Виталий", "Кульша", 375441234567L, "user@gmail.com", Role.READER);
        assertEquals(0, userDao.update(saveUser));
        assertEquals(1, userDao.update(updateUser));
        assertEquals(updateUser, userDao.findById(3));
        assertEquals(1, userDao.save(saveUser));
        assertEquals(saveUser, userDao.findById(5));
    }

    @Test
    public void findAll() throws DaoException {
        UserDao userDao = DaoFactory.instance().userDao();
        assertEquals(getAllUsers(), userDao.findAll());
    }

    @Test
    public void findById() throws DaoException {
        UserDao userDao = DaoFactory.instance().userDao();
        User expected = getAllUsers().stream()
                .filter(u -> u.getId() == 1)
                .findFirst()
                .get();
        assertEquals(expected, userDao.findById(1));
        assertNotEquals(expected, userDao.findById(2));
        assertNull(userDao.findById(10));
        assertNotNull(userDao.findById(3));
    }

    @Test
    public void deleteById() throws DaoException {
        UserDao userDao = DaoFactory.instance().userDao();
        assertEquals(1, userDao.deleteById(1));
        assertNull(userDao.findById(1));
        assertEquals(0, userDao.deleteById(10));
    }

    private List<User> getAllUsers() {
        return new ArrayList<>() {{
            this.add(new User(1, "Admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",
                    "Иван", "Иванов", 375123456789L, "admin@gmail.com", Role.ADMIN));
            this.add(new User(2, "Librarian", "2c445e1c04df4e247c2089245b68fc811f728f7d30ff14a6d64a4faac58e6270",
                    "Петр", "Петров", 375291234567L, "librarian@gmail.com", Role.ADMIN));
            this.add(new User(3, "User", "04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb",
                    "Сергей", "Сергеев", 375441234567L, "user@gmail.com", Role.READER));
            this.add(new User(4, "Reader", "3d0941964aa3ebdcb00ccef58b1bb399f9f898465e9886d5aec7f31090a0fb30",
                    "Олег", "Олегов", 375331234567L, "reader@gmail.com", Role.READER));
        }};
    }
}