package io.github.vitalikulsha.javawebproject.book.dao;

import io.github.vitalikulsha.javawebproject.book.entity.Book;
import io.github.vitalikulsha.javawebproject.util.dao.AbstractDao;
import io.github.vitalikulsha.javawebproject.util.dao.Dao;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.util.dao.queryoperator.constant.Column;

import java.util.List;

/**
 * Book DAO interface
 * See also:
 * {@link Dao}
 * {@link AbstractDao}
 */
public interface BookDao extends Dao<Book> {

    /**
     * Finds a book by title with pagination.
     *
     * @param title book title to search
     * @return found list of book
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    List<Book> findByBookTitle(int firstIndex, int itemsOnPage, String title) throws DaoException;

    /**
     * Finds a book by author with pagination.
     *
     * @param name author last name for book search
     * @return found list of book
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    List<Book> findByAuthorName(int firstIndex, int itemsOnPage, String name) throws DaoException;

    /**
     * Finds a book by category with pagination.
     *
     * @param name category name for book search
     * @return found list of book
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    List<Book> findByCategoryName(int firstIndex, int itemsOnPage, String name) throws DaoException;

    /**
     * Counts the number of books according to the given parameters.
     *
     * @param column      book search parameter
     * @param searchParam search column
     * @return number of books found
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    int countBySearchParam(Column column, String searchParam) throws DaoException;
}
