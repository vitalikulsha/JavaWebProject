package io.github.vitalikulsha.javawebproject.author.dao;

import io.github.vitalikulsha.javawebproject.author.entity.Author;
import io.github.vitalikulsha.javawebproject.util.dao.AbstractDao;
import io.github.vitalikulsha.javawebproject.util.dao.Dao;
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
     * Finds authors by book id.
     *
     * @param bookId book id to search for authors
     * @return list of found authors
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    List<Author> findAuthorsByBookId(int bookId) throws DaoException;
}
