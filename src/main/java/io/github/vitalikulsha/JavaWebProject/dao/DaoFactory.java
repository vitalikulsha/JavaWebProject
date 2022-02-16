package io.github.vitalikulsha.JavaWebProject.dao;

public class DaoFactory {

    public BookDao bookDao() {
        return new BookDaoIml();
    }

    public CategoryDao categoryDao() {
        return new CategoryDaoIml();
    }
}
