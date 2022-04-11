package io.github.vitalikulsha.javawebproject.util.dao;

import io.github.vitalikulsha.javawebproject.author.dao.AuthorDao;
import io.github.vitalikulsha.javawebproject.book.dao.BookDao;
import io.github.vitalikulsha.javawebproject.category.dao.CategoryDao;
import io.github.vitalikulsha.javawebproject.exception.DaoException;
import io.github.vitalikulsha.javawebproject.order.dao.OrderDao;
import io.github.vitalikulsha.javawebproject.user.dao.UserDao;

import java.util.List;

/**
 * DAO interface
 * See also:
 * {@link AbstractDao}
 * {@link BookDao}
 * {@link AuthorDao}
 * {@link CategoryDao}
 * {@link OrderDao}
 * {@link UserDao}
 *
 * @param <T> element/entity type in this DAO
 */
public interface Dao<T> {

    /**
     * Finds an entity by id.
     *
     * @param id entity id to search
     * @return found entity
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    T findById(int id) throws DaoException;

    /**
     * Finds all entities.
     *
     * @return found list of entities
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    List<T> findAll() throws DaoException;

    /**
     * Finds all entities with pagination.
     *
     * @param firstIndex  start index for pagination
     * @param itemsOnPage number of items per page
     * @return found list of entities
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    List<T> findAll(int firstIndex, int itemsOnPage) throws DaoException;

    /**
     * Saves an entity to the database.
     *
     * @param t entity to save
     * @return database query result
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    int save(T t) throws DaoException;

    /**
     * Deletes an entity by id.
     *
     * @param id entity id to delete
     * @return database query result
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    int deleteById(int id) throws DaoException;

    /**
     * Updates an entity to the database.
     *
     * @param t entity to update
     * @return database query result
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    int update(T t) throws DaoException;

    /**
     * Counts the number of all entries.
     *
     * @return number of entries found
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    int countAll() throws DaoException;
}

