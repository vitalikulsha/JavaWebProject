package io.github.vitalikulsha.JavaWebProject.service;

import io.github.vitalikulsha.JavaWebProject.entity.dto.OrderDto;
import io.github.vitalikulsha.JavaWebProject.entity.ReserveStatus;

import java.util.List;

public interface OrderService extends Service<OrderDto> {
    List<OrderDto> getOrdersByUserId(int userId);

    boolean createOrder(int bookId, int userId, ReserveStatus reserveStatus);

    boolean updateOrderApproval(boolean approved, int orderId);

    boolean updateOrderReserveStatus(ReserveStatus reserveStatus, int orderId);
}
