package io.github.vitalikulsha.javawebproject.book.service;

import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.util.service.Service;

import java.util.List;

/**
 * Book DTO service interface
 * See also:
 * {@link Service}
 */
public interface BookService extends Service<BookDTO> {
    /**
     * Gets a list of books DTO by title.
     *
     * @param title book title
     * @return list of book DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<BookDTO> getBooksByTitle(String title) throws ServiceException;

    /**
     * Gets a list of books DTO by author.
     *
     * @param authorName author's last name
     * @return list of book DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<BookDTO> getBooksByAuthorName(String authorName) throws ServiceException;

    /**
     * Gets a list of books DTO by category.
     *
     * @param categoryName name of category
     * @return list of book DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<BookDTO> getBooksByCategoryName(String categoryName) throws ServiceException;

    /**
     * Decreases the number of books with the given id by one.
     *
     * @param bookId book id
     * @throws ServiceException thrown when DAO exception occurs
     */
    void decrementQuantityBook(int bookId) throws ServiceException;

    /**
     * Increases the number of books with the given id by one.
     *
     * @param bookId book id
     * @throws ServiceException thrown when DAO exception occurs
     */
    void incrementQuantityBook(int bookId) throws ServiceException;
}
