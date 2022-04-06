package io.github.vitalikulsha.javawebproject.order.service;

import io.github.vitalikulsha.javawebproject.order.entity.OrderDTO;
import io.github.vitalikulsha.javawebproject.order.entity.ReserveStatus;
import io.github.vitalikulsha.javawebproject.exception.ServiceException;
import io.github.vitalikulsha.javawebproject.util.service.Service;

import java.util.List;

/**
 * Order DTO service interface
 * See also:
 * {@link Service}
 */
public interface OrderService extends Service<OrderDTO> {
    /**
     * Gets a list of orders DTO for the user with the given id.
     *
     * @param userId user id
     * @return list of order DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<OrderDTO> getOrdersByUserId(int userId) throws ServiceException;

    /**
     * Creates a new order.
     *
     * @param bookId        book id
     * @param userId        user id
     * @param reserveStatus order reserve status
     * @throws ServiceException thrown when DAO exception occurs
     */
    void createOrder(int bookId, int userId, ReserveStatus reserveStatus) throws ServiceException;

    /**
     * Updates the approval status of an order.
     *
     * @param approved order approval status
     * @param orderId  order id
     * @throws ServiceException thrown when DAO exception occurs
     */
    void updateOrderApproval(boolean approved, int orderId) throws ServiceException;

    /**
     * Updates the status of an order reservation.
     *
     * @param reserveStatus order reserve status
     * @param orderId       order id
     * @throws ServiceException thrown when DAO exception occurs
     */
    void updateOrderReserveStatus(ReserveStatus reserveStatus, int orderId) throws ServiceException;
}
