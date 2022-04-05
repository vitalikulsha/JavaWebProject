package io.github.vitalikulsha.javawebproject.book.service;

import io.github.vitalikulsha.javawebproject.book.entity.dto.BookDto;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.util.service.Service;

import java.util.List;

/**
 * Book DTO service interface
 * See also:
 * {@link Service}
 */
public interface BookService extends Service<BookDto> {
    /**
     * Gets a list of books DTO by title.
     *
     * @param title book title
     * @return list of book DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<BookDto> getBooksByTitle(String title) throws ServiceException;

    /**
     * Gets a list of books DTO by author.
     *
     * @param authorName author's last name
     * @return list of book DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<BookDto> getBooksByAuthorName(String authorName) throws ServiceException;

    /**
     * Gets a list of books DTO by category.
     *
     * @param categoryName name of category
     * @return list of book DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<BookDto> getBooksByCategoryName(String categoryName) throws ServiceException;

    /**
     * Decreases the number of books with the given id by one.
     *
     * @param bookId book id
     * @return true if the book was successfully deleted
     * @throws ServiceException thrown when DAO exception occurs
     */
    boolean removeOneBook(int bookId) throws ServiceException;

    /**
     * Increases the number of books with the given id by one.
     *
     * @param bookId book id
     * @return true if the book was successfully added
     * @throws ServiceException thrown when DAO exception occurs
     */
    boolean addOneBook(int bookId) throws ServiceException;
}
