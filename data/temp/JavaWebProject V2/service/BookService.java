package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.dao.BookDao;
import io.github.vitalikulsha.JavaWebProject.dao.DaoFactory;
import io.github.vitalikulsha.JavaWebProject.entity.Book;

import java.util.List;

public class BookService implements Service<Book>{

    @Override
    public Book getById(int id) {
        BookDao bookDao = DaoFactory.instance().bookDao();
        Book book = bookDao.getById(id);
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public void delete(Book book) {

    }
}
