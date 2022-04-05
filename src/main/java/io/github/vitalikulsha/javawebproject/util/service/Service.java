package io.github.vitalikulsha.javawebproject.util.service;

import io.github.vitalikulsha.javawebproject.book.service.BookService;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.order.service.OrderService;
import io.github.vitalikulsha.javawebproject.user.service.UserService;

import java.util.List;

/**
 * Service interface
 * See also:
 * {@link OrderService}
 * {@link BookService}
 * {@link UserService}
 *
 * @param <T> element/entity type in this service
 */
public interface Service<T> {
    /**
     * Gets entity by entity id.
     *
     * @param id id of the entity to be searched
     * @return the resulting entity
     * @throws ServiceException thrown when DAO exception occurs
     */
    T getById(int id) throws ServiceException;

    /**
     * Gets all entities.
     *
     * @return the resulting list of objects
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<T> getAll() throws ServiceException;

    /**
     * Permanently deleting an entity.
     *
     * @param id id of the entity to be deleted
     * @return true if entity was successfully deleted
     * @throws ServiceException thrown when DAO exception occurs
     */
    boolean deleteById(int id) throws ServiceException;
}
