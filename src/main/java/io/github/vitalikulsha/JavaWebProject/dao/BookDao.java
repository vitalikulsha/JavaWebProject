package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.Book;
import io.github.vitalikulsha.JavaWebProject.exception.DaoException;

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

    /**
     * Updates the quantity of books in the library
     *
     * @param quantityBooks new value for the quantity of books in the library
     * @param bookId        id of the book whose quantity needs to be updated
     * @return database query result
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    int updateQuantityBooks(int quantityBooks, int bookId) throws DaoException;
}
