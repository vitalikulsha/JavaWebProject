package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.entity.Author;
import io.github.vitalikulsha.JavaWebProject.exception.DaoException;

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
