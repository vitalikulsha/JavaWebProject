package io.github.vitalikulsha.javawebproject.order.dao;

import io.github.vitalikulsha.javawebproject.util.dao.AbstractDao;
import io.github.vitalikulsha.javawebproject.util.dao.Dao;
import io.github.vitalikulsha.javawebproject.order.entity.Order;
import io.github.vitalikulsha.javawebproject.exception.DaoException;

import java.util.List;

/**
 * Order DAO interface
 * See also:
 * {@link Dao}
 * {@link AbstractDao}
 */
public interface OrderDao extends Dao<Order> {
    /**
     * Finds a list of user orders with the given id.
     *
     * @param userId user id to search
     * @return found list of orders
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    List<Order> findByUserId(int userId) throws DaoException;
}
