package io.github.vitalikulsha.JavaWebProject.dao;

import io.github.vitalikulsha.JavaWebProject.exception.DaoException;

import java.util.List;

/**
 * DAO interface
 *  See also:
 *  {@link AbstractDao}
 *  {@link BookDao}
 *  {@link AuthorDao}
 *  {@link CategoryDao}
 *  {@link OrderDao}
 *  {@link UserDao}
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
     * Saves an entity to the database.
     *
     * @param t entity to save
     * @return result of database query
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    int save(T t) throws DaoException;

    /**
     * Deletes an entity by id.
     *
     * @param id entity id to delete
     * @return result of database query
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    int deleteById(int id) throws DaoException;
}

