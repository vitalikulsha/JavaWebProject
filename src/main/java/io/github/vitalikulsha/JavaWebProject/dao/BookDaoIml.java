package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.model.Book;

import java.util.List;
import java.util.Optional;

public class BookDaoIml implements BookDao{
    @Override
    public Optional<Book> getById(Long Id) {
        return Optional.empty();
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
