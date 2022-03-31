package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.entity.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus;
import io.github.vitalikulsha.JavaWebProject.exception.ServiceException;

import java.util.List;

/**
 * Order service interface
 */
public interface OrderService extends Service<OrderDto> {
    /**
     * Gets a list of orders DTO for the user with the given id.
     *
     * @param userId user id
     * @return list of order DTO
     * @throws ServiceException thrown when DAO exception occurs
     */
    List<OrderDto> getOrdersByUserId(int userId) throws ServiceException;

    /**
     * Creates a new order.
     *
     * @param bookId        book id
     * @param userId        user id
     * @param reserveStatus order reserve status
     * @return true if order was successfully created
     * @throws ServiceException thrown when DAO exception occurs
     */
    boolean createOrder(int bookId, int userId, ReserveStatus reserveStatus) throws ServiceException;

    /**
     * Updates the approval status of an order.
     *
     * @param approved order approval status
     * @param orderId  order id
     * @return true if order approval status was successfully updated
     * @throws ServiceException
     */
    boolean updateOrderApproval(boolean approved, int orderId) throws ServiceException;

    /**
     * Updates the status of an order reservation
     *
     * @param reserveStatus order reserve status
     * @param orderId       order id
     * @return true if order approval status was successfully updated
     * @throws ServiceException
     */
    boolean updateOrderReserveStatus(ReserveStatus reserveStatus, int orderId) throws ServiceException;
}
