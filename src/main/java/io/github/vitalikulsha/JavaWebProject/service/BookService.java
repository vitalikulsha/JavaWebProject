package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.entity.dto.BookDto;
import io.github.vitalikulsha.JavaWebProject.exception.ServiceException;

import java.util.List;

public interface BookService extends Service<BookDto> {

    List<BookDto> getBooksByTitle(String title) throws ServiceException;

    List<BookDto> getBooksByAuthorName(String authorName) throws ServiceException;

    List<BookDto> getBooksByCategoryName(String categoryName) throws ServiceException;

    boolean removeOneBook(int bookId) throws ServiceException;

    boolean addOneBook(int bookId) throws ServiceException;
}
