package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.entity.dto.BookDto;

import java.util.List;

public interface BookService extends Service<BookDto> {

    List<BookDto> getBooksByTitle(String title);

    List<BookDto> getBooksByAuthorName(String authorName);

    List<BookDto> getBooksByCategoryName(String categoryName);

    boolean removeOneBook(int bookId);

    boolean addOneBook(int bookId);
}
