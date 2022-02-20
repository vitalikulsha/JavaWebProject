package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.domain.Book;

import java.util.List;

public interface BookDao extends Dao<Book, Integer>{
    List<Book> getByName(String title);

    List<Book> getByAuthorId(Integer authorId);

    List<Book> getByCategoryId(Integer categoryId);
}
