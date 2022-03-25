package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.exception.DaoException;

import java.util.List;


public interface BookDao extends Dao<Book>{
    List<Book> findByBookTitle(String title) throws DaoException;

    List<Book> findByAuthorName(String name) throws DaoException;

    List<Book> findByCategoryName(String name) throws DaoException;

    int updateQuantityBooks(int numberBooks, int bookId) throws DaoException;
}
