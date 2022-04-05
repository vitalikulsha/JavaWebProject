package io.github.vitalikulsha.javawebproject.dao;

import io.github.vitalikulsha.javawebproject.entity.Order;
import io.github.vitalikulsha.javawebproject.entity.ReserveStatus;
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

    /**
     * Updates the approval status of an order.
     *
     * @param approved new order approval status
     * @param orderId  order id to update
     * @return database query result
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    int updateApproved(boolean approved, int orderId) throws DaoException;

    /**
     * Updates the reserve status of an order.
     *
     * @param reserveStatus new order reserve status
     * @param orderId       order id to update
     * @return database query result
     * @throws DaoException thrown when DAO exception occurs while executing a query
     */
    int updateReserved(ReserveStatus reserveStatus, int orderId) throws DaoException;
}
