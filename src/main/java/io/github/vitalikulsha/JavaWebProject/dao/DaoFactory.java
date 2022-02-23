package io.github.vitalikulsha.JavaWebProject.dao;

public class DaoFactory {

    public BookDao bookDao() {
        return new BookDaoImpl();
    }

    public CategoryDao categoryDao() {
        return new CategoryDaoImpl();
    }

    public AuthorDao authorDao() {
        return new AuthorDaoImpl();
    }

    public RecordBookDao bookCatalogDao() {
        return new RecordBookDaoImpl();
    }

    public UserDao userDao() {
        return new UserDaoIml();
    }

    public OrderDao orderDao() {
        return new OrderDaoImpl();
    }
}
