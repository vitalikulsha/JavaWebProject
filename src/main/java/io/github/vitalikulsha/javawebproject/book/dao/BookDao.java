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
     * Finds a book by title.
     *
     * @param title book title to search
     * @return found list of book
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    List<Book> findByBookTitle(String title) throws DaoException;

    /**
     * Finds a book by author.
     *
     * @param name author last name for book search
     * @return found list of book
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    List<Book> findByAuthorName(String name) throws DaoException;

    /**
     * Finds a book by category.
     *
     * @param name category name for book search
     * @return found list of book
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    List<Book> findByCategoryName(String name) throws DaoException;

    List<Book> findAllPagination(int firstLow, int lastLow) throws DaoException;

    int countBySearchParam(String searchParam, Column column) throws DaoException;
}
