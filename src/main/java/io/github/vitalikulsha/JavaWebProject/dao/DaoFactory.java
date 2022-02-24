package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.dao.impl.*;

public class DaoFactory {
    private final static DaoFactory instance = new DaoFactory();

    private final static BookDao bookDao = new BookDaoImpl();
    private final static RecordBookDao recordBookDao = new RecordBookDaoImpl();
    private final static UserDao userDao = new UserDaoIml();
    private final static OrderDao orderDao = new OrderDaoImpl();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public RecordBookDao getBookCatalogDao() {
        return recordBookDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }
}
