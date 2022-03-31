package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.exception.ServiceException;

import java.util.List;

/**
 * Service interface
 *
 * @param <T> entity type
 */
public interface Service<T> {
    /**
     * Gets entity by entity id.
     *
     * @param id entity id
     * @return entity
     * @throws ServiceException thrown when DAO exception occurs
     */
    T getById(int id) throws ServiceException;

    /**
     * Gets all entities
     *
     * @return entity list
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<T> getAll() throws ServiceException;

    /**
     * Permanently deleting an entity
     *
     * @param id entity id
     * @return true if entity was successfully deleted
     * @throws ServiceException thrown when DAO exception occurs
     */
    boolean deleteById(int id) throws ServiceException;
}
