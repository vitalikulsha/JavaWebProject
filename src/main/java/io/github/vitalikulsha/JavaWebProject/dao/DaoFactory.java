package io.github.vitalikulsha.JavaWebProject.dao;

public class DaoFactory <T extends Dao > {

    public BookDao bookDao() {
        return new BookDaoImpl();
    }

    public CategoryDao categoryDao() {
        return new CategoryDaoImpl();
    }

    public AuthorDao authorDao() {
        return new AuthorDaoImpl();
    }
}
