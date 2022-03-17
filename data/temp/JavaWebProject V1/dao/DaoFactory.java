package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.dao.impl.*;

public class DaoFactory {
    private final static DaoFactory instance = new DaoFactory();

    private final static BookDao bookDao = new BookDaoImpl();
    private final static RecordBookDao recordBookDao = new RecordBookDaoImpl();
    private final static UserDao userDao = new UserDaoIml();
    private final static OrderDao orderDao = new OrderDaoImpl();
    private final static CategoryDao categoryDao = new CategoryDaoImpl();
    private final static AuthorDao authorDao = new AuthorDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory instance() {
        return instance;
    }

    public BookDao bookDao() {
        return bookDao;
    }

    public RecordBookDao recordBookDao() {
        return recordBookDao;
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
