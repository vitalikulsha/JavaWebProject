package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.Book;

import java.util.List;


public interface BookDao extends Dao<Book>{
    List<Book> getByBookTitle(String title);

    List<Book> getByAuthorName(String name);

    List<Book> getByCategoryName(String name);
}
