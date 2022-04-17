package io.github.vitalikulsha.javawebproject.util.dao;

import io.github.vitalikulsha.javawebproject.author.dao.AuthorDao;
import io.github.vitalikulsha.javawebproject.author.dao.AuthorDaoImpl;
import io.github.vitalikulsha.javawebproject.book.dao.BookDao;
import io.github.vitalikulsha.javawebproject.book.dao.BookDaoImpl;
import io.github.vitalikulsha.javawebproject.category.dao.CategoryDao;
import io.github.vitalikulsha.javawebproject.category.dao.CategoryDaoImpl;
import io.github.vitalikulsha.javawebproject.order.dao.OrderDao;
import io.github.vitalikulsha.javawebproject.order.dao.OrderDaoImpl;
import io.github.vitalikulsha.javawebproject.user.dao.UserDao;
import io.github.vitalikulsha.javawebproject.user.dao.UserDaoIml;

/**
 * Factory, that provides DAO.
 */
public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private static final BookDao bookDao = new BookDaoImpl();
    private static final UserDao userDao = new UserDaoIml();
    private static final OrderDao orderDao = new OrderDaoImpl();
    private static final CategoryDao categoryDao = new CategoryDaoImpl();
    private static final AuthorDao authorDao = new AuthorDaoImpl();

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
