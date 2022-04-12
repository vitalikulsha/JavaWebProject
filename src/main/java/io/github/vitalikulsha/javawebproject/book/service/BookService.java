package io.github.vitalikulsha.javawebproject.book.service;

import io.github.vitalikulsha.javawebproject.book.entity.BookDTO;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.util.Pagination;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;
import io.github.vitalikulsha.javawebproject.util.service.Service;

import java.util.List;

/**
 * Book DTO service interface
 * See also:
 * {@link Service}
 */
public interface BookService extends Service<BookDTO> {

    /**
     * Gets a paginated list of DTO books by title.
     *
     * @param pagination an object of the Pagination class with pagination parameters
     * @param title      book title
     * @return list of book DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<BookDTO> getBooksByTitle(Pagination pagination, String title) throws ServiceException;

    /**
     * Gets a paginated list of DTO books by author.
     *
     * @param pagination an object of the Pagination class with pagination parameters
     * @param authorName  author's last name
     * @return list of book DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<BookDTO> getBooksByAuthorName(Pagination pagination, String authorName) throws ServiceException;

    /**
     * Gets a paginated list of DTO books by category.
     *
     * @param pagination an object of the Pagination class with pagination parameters
     * @param categoryName name of category
     * @return list of book DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<BookDTO> getBooksByCategoryName(Pagination pagination, String categoryName) throws ServiceException;

    /**
     * Counts the number of books according to the given parameters.
     *
     * @param column      book search parameter
     * @param searchParam search column
     * @return number of books found
     * @throws ServiceException thrown when DAO exception occurs
     */
    int countBySearchParam(Column column, String searchParam) throws ServiceException;

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
