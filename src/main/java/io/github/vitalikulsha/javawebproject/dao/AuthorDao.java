package io.github.vitalikulsha.javawebproject.dao;

import io.github.vitalikulsha.javawebproject.entity.Author;
import io.github.vitalikulsha.javawebproject.exception.DaoException;

import java.util.List;

/**
 * Author DAO interface
 *  See also:
 *  {@link Dao}
 *  {@link AbstractDao}
 */
public interface AuthorDao extends Dao<Author> {
    /**
     * Find authors by book id.
     *
     * @param bookId book id to search for authors
     * @return list of found authors
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    List<Author> findAuthorsByBookId(int bookId) throws DaoException;
}
