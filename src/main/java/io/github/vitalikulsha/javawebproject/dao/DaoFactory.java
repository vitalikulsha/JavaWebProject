package io.github.vitalikulsha.javawebproject.dao;

import io.github.vitalikulsha.javawebproject.dao.impl.*;

/**
 * Factory, that provides DAO.
 */
public class DaoFactory {
    private final static DaoFactory instance = new DaoFactory();

    private final static BookDao bookDao = new BookDaoImpl();
    private final static UserDao userDao = new UserDaoIml();
    private final static OrderDao orderDao = new OrderDaoImpl();
    private final static CategoryDao categoryDao = new CategoryDaoImpl();
    private final static AuthorDao authorDao = new AuthorDaoImpl();

    private DaoFactory() {
    }

    /**
     * Gets instance.
     *
     * @return instance of DaoFactory
     */
    public static DaoFactory instance() {
        return instance;
    }

    public BookDao bookDao() {
        return bookDao;
    }

    public UserDao userDao() {
        return userDao;
    }

    public OrderDao orderDao() {
        return orderDao;
    }

    public CategoryDao categoryDao() {
        return categoryDao;
    }

    public AuthorDao authorDao() {
        return authorDao;
    }
}
