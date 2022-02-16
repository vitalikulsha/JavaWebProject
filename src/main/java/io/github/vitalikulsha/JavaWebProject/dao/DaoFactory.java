package io.github.vitalikulsha.JavaWebProject.dao;

public class DaoFactory {

    public BookDao bookDao() {
        return new BookDaoImpl();
    }

    public CategoryDao categoryDao() {
        return new CategoryDaoImpl();
    }
}
