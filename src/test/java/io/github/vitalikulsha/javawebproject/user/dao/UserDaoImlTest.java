package io.github.vitalikulsha.javawebproject.user.dao;

import io.github.vitalikulsha.javawebproject.DataBase;
import io.github.vitalikulsha.javawebproject.Paging;
import io.github.vitalikulsha.javawebproject.config.ConfigParameter;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.user.entity.Role;
import io.github.vitalikulsha.javawebproject.user.entity.User;
import io.github.vitalikulsha.javawebproject.util.dao.DaoFactory;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoImlTest {
    UserDao userDao;
    Paging<User> paging;
    List<User> userList;

    @Before
    public void initial() {
        userDao = DaoFactory.instance().userDao();
        paging = new Paging<>(1, ConfigParameter.ITEMS_ON_PAGE);
        userList = DataBase.USER_TABLE;
    }

    @Test
    public void findByLogin() throws DaoException {
        User expected = userList.stream()
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
        User expected = userList.stream()
                .filter(u -> u.getEmail().equals("admin@gmail.com"))
                .findFirst()
                .get();
        assertEquals(expected, userDao.findByEmail("admin@gmail.com"));
        assertNotEquals(expected, userDao.findByEmail("reader@gmail.com"));
        assertNull(userDao.findByEmail("test@gmail.com"));
        assertNotNull(userDao.findByEmail("user@gmail.com"));
    }

    @Test
    public void findByRole() throws DaoException {
        List<User> expected = userList.stream()
                .filter(u -> u.getRole()==Role.READER)
                .collect(Collectors.toList());
        assertEquals(paging.paginate(expected),
                userDao.findByRole(0,ConfigParameter.ITEMS_ON_PAGE, Role.READER));
        assertTrue(userDao.findByRole(15, ConfigParameter.ITEMS_ON_PAGE, Role.READER).isEmpty());
    }

    @Test
    public void findAll() throws DaoException {
        assertEquals(userList, userDao.findAll());
        assertEquals(paging.paginate(userList),
                userDao.findAll(0, ConfigParameter.ITEMS_ON_PAGE));
        assertTrue(userDao.findAll(10, ConfigParameter.ITEMS_ON_PAGE).isEmpty());
    }

    @Test
    public void findById() throws DaoException {
        User expected = userList.stream()
                .filter(u -> u.getId() == 1)
                .findFirst()
                .get();
        assertEquals(expected, userDao.findById(1));
        assertNotEquals(expected, userDao.findById(2));
        assertNull(userDao.findById(10));
        assertNotNull(userDao.findById(3));
    }

    @Test
    public void updateMethods() throws DaoException {
        User saveUser = new User(5, "Test", "test", "Test", "Test",
                375123456789L, "test@test.by", Role.GUEST);
        User updateUser = new User(3, "User", "04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb",
                "Виталий", "Кульша", 375441234567L, "user@gmail.com", Role.READER);
        assertEquals(0, userDao.update(saveUser));
        assertEquals(1, userDao.update(updateUser));
        assertEquals(updateUser, userDao.findById(3));

        assertEquals(1, userDao.save(saveUser));
        assertEquals(saveUser, userDao.findById(5));

        assertEquals(1, userDao.deleteById(5));
        assertNull(userDao.findById(5));
        assertEquals(0, userDao.deleteById(15));
    }

    @Test
    public void countAllAndByRoleParam() throws DaoException {
        assertEquals(userList.size(), userDao.countAll());
        long expected = userList.stream()
                .filter(u -> u.getRole()==Role.READER)
                .count();
        assertEquals(expected, userDao.countByRoleParam(Role.READER));
        assertEquals(0, userDao.countByRoleParam(Role.GUEST));
    }
}