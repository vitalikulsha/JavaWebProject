package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.Book;

import java.util.List;


public interface BookDao extends Dao<Book>{
    List<Book> findByBookTitle(String title);

    List<Book> findByAuthorName(String name);

    List<Book> findByCategoryName(String name);

    int updateNumberBooks(int numberBooks, int bookId);
}
