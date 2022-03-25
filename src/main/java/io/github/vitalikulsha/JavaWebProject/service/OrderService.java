package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.entity.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus;
import io.github.vitalikulsha.JavaWebProject.exception.ServiceException;

import java.util.List;

public interface OrderService extends Service<OrderDto> {
    List<OrderDto> getOrdersByUserId(int userId) throws ServiceException;

    boolean createOrder(int bookId, int userId, ReserveStatus reserveStatus) throws ServiceException;

    boolean updateOrderApproval(boolean approved, int orderId) throws ServiceException;

    boolean updateOrderReserveStatus(ReserveStatus reserveStatus, int orderId) throws ServiceException;
}
