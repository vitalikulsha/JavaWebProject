package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.Book;

import java.util.List;

public interface BookDao extends Dao<Book>{
    List<Book> getByName(String title);

    List<Book> getByCategoryName(String categoryName);

    List<Book> getByAuthorName(String authorName);

//    List<Book> getByAuthorId(Integer authorId);

//    List<Book> getByCategoryId(Integer categoryId);
}
